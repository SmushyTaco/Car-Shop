{
    function buyProductAlert(): void {
        alert("You have successfully purchased this product!");
    }

    document.addEventListener("DOMContentLoaded", () => {
        document.querySelectorAll(".buy-product-button").forEach((button) => {
            button.addEventListener("click", buyProductAlert);
        });
    });
}