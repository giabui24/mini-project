import react from '@vitejs/plugin-react';
import {fileURLToPath} from 'node:url';
import type {OutputAsset, OutputChunk} from 'rollup';
import {defineConfig, type Plugin} from 'vite';

function injectCssIntoGlobalJs(): Plugin {
    return {
        apply: 'build',
        enforce: 'post',
        generateBundle(_options, bundle) {
            const cssAsset = Object.values(bundle).find(
                (item): item is OutputAsset =>
                    item.type === 'asset' && item.fileName.endsWith('.css')
            );
            const entryChunk = Object.values(bundle).find(
                (item): item is OutputChunk =>
                    item.type === 'chunk' && item.isEntry
            );

            if (!cssAsset || !entryChunk) {
                return;
            }

            const css =
                typeof cssAsset.source === 'string'
                    ? cssAsset.source
                    : Buffer.from(cssAsset.source).toString();
            const styleLoader = `const styleId="nexcent-landing-elements-styles";if(!document.getElementById(styleId)){const style=document.createElement("style");style.id=styleId;style.textContent=${JSON.stringify(css)};document.head.appendChild(style);}`;

            entryChunk.code = `${styleLoader}${entryChunk.code}`;

            delete bundle[cssAsset.fileName];
        },
        name: 'inject-css-into-global-js',
    };
}

export default defineConfig({
    build: {
        emptyOutDir: true,
        lib: {
            entry: fileURLToPath(new URL('./src/index.tsx', import.meta.url)),
            fileName: () => 'index.js',
            formats: ['es'],
        },
        outDir: 'build',
        rollupOptions: {
            output: {
                assetFileNames: (assetInfo) =>
                    assetInfo.name?.endsWith('.css')
                        ? 'style.css'
                        : 'assets/[name][extname]',
            },
        },
    },
    plugins: [react(), injectCssIntoGlobalJs()],
});
