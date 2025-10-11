{
    const prefersDarkScheme: MediaQueryList = globalThis.matchMedia("(prefers-color-scheme: dark)");
    document.documentElement.dataset.bsTheme = prefersDarkScheme.matches ? "dark" : "light";
    prefersDarkScheme.addEventListener("change", (event) => {
        document.documentElement.dataset.bsTheme = event.matches ? "dark" : "light";
    });
}