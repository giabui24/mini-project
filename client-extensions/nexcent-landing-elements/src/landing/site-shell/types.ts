export type AccountContext = {
    accountURL: string;
    createAccountURL: string;
    displayName: string;
    emailAddress: string;
    loginURL: string;
    logoutURL: string;
    portraitURL: string;
    signedIn: boolean;
};

export type NavigationItem = {
    children: NavigationItem[];
    externalReferenceCode: string;
    label: string;
    selected: boolean;
    target: string;
    url: string;
};
