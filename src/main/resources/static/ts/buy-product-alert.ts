{
    function buyProductAlert(): void {
        alert("You have successfully purchased this product!");
    }

    document.addEventListener("DOMContentLoaded", () => {
        const buttons = document.querySelectorAll(".buy-product-button");
        for (const button of buttons) {
            button.addEventListener("click", buyProductAlert);
        }
    });
}