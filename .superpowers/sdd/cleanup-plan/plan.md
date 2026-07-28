# Cleanup Plan: Nexcent client extensions

## Summary
Production chỉ giữ nexcent-react-*, nexcent-contact-form và nexcent-article-detail.
nexcent-react-page chỉ phục vụ Vite preview, không được đóng gói thành fragment production.
Giữ nexcent-global-css riêng với themeCSS, nhưng chuyển source sang SCSS có phân lớp rõ ràng và compile ra một artifact CSS.
Loại bỏ lab, importer và các Hero/Services/Features đời cũ khỏi source, bundle, Liferay metadata và dependency tree.

## Global Constraints
- Giữ nguyên tên và attributes của toàn bộ nexcent-react-* custom elements
- Giữ nguyên nexcent-contact-form, configuration fields và submit API
- Giữ nguyên nexcent-article-detail và URL contract
- nexcent-react-page tồn tại trong JS runtime nhưng không là fragment production
- Chạy npm run typecheck, npm test, npm run build theo thứ tự; tất cả pass
- Contact Us vẫn production, phải giữ đầy đủ
- Article Detail vẫn được sử dụng
- Việc đổi font hosting hoặc thiết kế giao diện không nằm trong đợt refactor này

## Tasks

### Task 1: Legacy code cleanup
Remove dead standalone components (Lab, Importer, Hero, Services, Features, ContactForm old).
- Xoá src/components/ toàn bộ
- Xoá src/App.tsx
- Xoá src/styles/
- Xoá src/liferay/ (nếu không còn dùng)
- Clean src/index.tsx: chỉ giữ registerStaticElements()
- Clean client-extension.yaml: chỉ giữ nexcent-react-runtime
- Clean package.json: xoá exceljs, generate:workbook, validate:data-sources
- Clean api/structuredContent.ts: xoá helpers chỉ dùng bởi dead code (readText, readNumber, readBoolean, readImage, flattenContentFields)
- Xoá stories file chết: ContactForm.stories (nếu ContactForm cũ đã xoá)

### Task 2: Theme SCSS restructure
Convert global.css to SCSS với phân lớp rõ ràng.
- Tạo src/scss/ với các partials:
  - abstracts/_variables.scss (CSS custom properties)
  - base/_reset.scss (body, html, font)
  - layout/_container.scss (.nxc-container, .nxc-section)
  - layout/_header.scss (.nxc-site-header)
  - layout/_footer.scss (.nxc-site-footer)
  - components/_react-hosts.scss (host integration cho nexcent-react-*)
  - utilities/_accessibility.scss (.nxc-visually-hidden)
- global.scss entrypoint @use các partials
- Compile ra assets/global.css
- Loại bỏ OOTB header/footer/newsletter rules không còn dùng
- Loại component-level classes (.nxc-card, .nxc-button, etc.)
- Thêm Sass build script, nối vào Gradle assembly

### Task 3: ContentSections split + CSS extraction + restructure
- Split ContentSections.tsx (~569 lines, 7 components) into sections/*/
  - Clients, Community, Feature, Statistics, Testimonial, Marketing, Cta
- Extract CSS from StaticStyleBoundary.tsx into shell/styles.css
- Restructure: static-site/ → sections/ + shell/
- Update all imports

### Task 4: Fragment packaging
- Remove nexcent-react-page from fragment production build
- Keep it registered for Vite preview only
- Clean reference-assets (remove unused files)
- Update fragment deploy descriptor (company: nextcen.com, group: Next Gen Site)

### Task 5: Verification
- npm run typecheck
- npm test
- npm run build
- Gradle client-extension assembly