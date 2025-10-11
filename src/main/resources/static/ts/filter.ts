{
    function saveSearchValues(): void {
        const outsourcedPartKeyword = (document.getElementById("outsourced-part-keyword") as HTMLInputElement).value || "";
        localStorage.setItem("outsourced-part-keyword", outsourcedPartKeyword);

        const inHousePartKeyword = (document.getElementById("in-house-part-keyword") as HTMLInputElement).value || "";
        localStorage.setItem("in-house-part-keyword", inHousePartKeyword);

        const productKeyword = (document.getElementById("product-keyword") as HTMLInputElement).value || "";
        localStorage.setItem("product-keyword", productKeyword);

        localStorage.setItem("is-reload", "true");
    }

    function searchOutsourcedParts(): void {
        saveSearchValues();
        const queryParams = new URLSearchParams(globalThis.location.search);
        const productKeyword = queryParams.get("product-keyword") || "";
        const inHousePartKeyword = queryParams.get("in-house-part-keyword") || "";
        const outsourcedPartKeyword = (document.getElementById("outsourced-part-keyword") as HTMLInputElement).value || "";
        globalThis.location.href = `/main-screen?outsourced-part-keyword=${encodeURIComponent(outsourcedPartKeyword)}&in-house-part-keyword=${encodeURIComponent(inHousePartKeyword)}&product-keyword=${encodeURIComponent(productKeyword)}`;
    }

    function searchInHouseParts(): void {
        saveSearchValues();
        const queryParams = new URLSearchParams(globalThis.location.search);
        const productKeyword = queryParams.get("product-keyword") || "";
        const outsourcedPartKeyword = queryParams.get("outsourced-part-keyword") || "";
        const inHousePartKeyword = (document.getElementById("in-house-part-keyword") as HTMLInputElement).value || "";
        globalThis.location.href = `/main-screen?outsourced-part-keyword=${encodeURIComponent(outsourcedPartKeyword)}&in-house-part-keyword=${encodeURIComponent(inHousePartKeyword)}&product-keyword=${encodeURIComponent(productKeyword)}`;
    }

    function searchProducts(): void {
        saveSearchValues();
        const queryParams = new URLSearchParams(globalThis.location.search);
        const outsourcedPartKeyword = queryParams.get("outsourced-part-keyword") || "";
        const inHousePartKeyword = queryParams.get("in-house-part-keyword") || "";
        const productKeyword = (document.getElementById("product-keyword") as HTMLInputElement).value || "";
        globalThis.location.href = `/main-screen?outsourced-part-keyword=${encodeURIComponent(outsourcedPartKeyword)}&in-house-part-keyword=${encodeURIComponent(inHousePartKeyword)}&product-keyword=${encodeURIComponent(productKeyword)}`;
    }

    function clearSearchOutsourcedParts(): void {
        (document.getElementById("outsourced-part-keyword") as HTMLInputElement).value = "";
        searchOutsourcedParts();
    }

    function clearSearchInHouseParts(): void {
        (document.getElementById("in-house-part-keyword") as HTMLInputElement).value = "";
        searchInHouseParts();
    }

    function clearSearchProducts(): void {
        (document.getElementById("product-keyword") as HTMLInputElement).value = "";
        searchProducts();
    }

    document.addEventListener("DOMContentLoaded", (): void => {
        const productSearchButton = document.getElementById("product-search");
        const clearProductSearchButton = document.getElementById("clear-product-search");
        if (productSearchButton) productSearchButton.addEventListener("click", searchProducts);
        if (clearProductSearchButton) clearProductSearchButton.addEventListener("click", clearSearchProducts);

        const outsourcedPartSearchButton = document.getElementById("outsourced-part-search");
        const clearOutsourcedPartSearchButton = document.getElementById("clear-outsourced-part-search");
        if (outsourcedPartSearchButton) outsourcedPartSearchButton.addEventListener("click", searchOutsourcedParts);
        if (clearOutsourcedPartSearchButton) clearOutsourcedPartSearchButton.addEventListener("click", clearSearchOutsourcedParts);

        const inHousePartSearchButton = document.getElementById("in-house-part-search");
        const clearInHousePartSearchButton = document.getElementById("clear-in-house-part-search");
        if (inHousePartSearchButton) inHousePartSearchButton.addEventListener("click", searchInHouseParts);
        if (clearInHousePartSearchButton) clearInHousePartSearchButton.addEventListener("click", clearSearchInHouseParts);

        if (localStorage.getItem("is-reload") !== "true") return;
        localStorage.setItem("is-reload", "false");

        const outsourcedPartKeyword = localStorage.getItem("outsourced-part-keyword");
        if (outsourcedPartKeyword) (document.getElementById("outsourced-part-keyword") as HTMLInputElement).value = outsourcedPartKeyword;

        const inHousePartKeyword = localStorage.getItem("in-house-part-keyword");
        if (inHousePartKeyword) (document.getElementById("in-house-part-keyword") as HTMLInputElement).value = inHousePartKeyword;

        const productKeyword = localStorage.getItem("product-keyword");
        if (productKeyword) (document.getElementById("product-keyword") as HTMLInputElement).value = productKeyword;
    });
}