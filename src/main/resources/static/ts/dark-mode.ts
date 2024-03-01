{
    const prefersDarkScheme: MediaQueryList = window.matchMedia("(prefers-color-scheme: dark)");
    document.documentElement.setAttribute("data-bs-theme", prefersDarkScheme.matches ? "dark" : "light");
    prefersDarkScheme.addEventListener("change", (event) => {
        document.documentElement.setAttribute("data-bs-theme", event.matches ? "dark" : "light");
    });
}