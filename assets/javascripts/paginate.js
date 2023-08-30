function paginate(items, itemsPerPage, paginationContainer) {
    let currentPage = 1;
    const totalPages = Math.ceil(items.length / itemsPerPage);

    function showItems(page) {
        const startIndex = (page - 1) * itemsPerPage;
        const endIndex = startIndex + itemsPerPage;
        const pageItems = items.slice(startIndex, endIndex);

        const itemsContainer = document.querySelector("div.plugins");
        itemsContainer.innerHTML = "";

        pageItems.forEach((item) => {
            const li = document.createElement("li");
            li.innerText = item.innerText;
            itemsContainer.appendChild(item);
        });
    }

    function setupPagination() {
        const pagination = document.querySelector(paginationContainer);
        pagination.innerHTML = "";

        for (let i = 1; i <= totalPages; i++) {
            const link = document.createElement("a");
            link.href = "#";
            link.innerText = i;

            if (i === currentPage) {
                link.classList.add("active");
            }

            link.addEventListener("click", (event) => {
                event.preventDefault();
                currentPage = i;
                showItems(currentPage);

                const currentActive = pagination.querySelector(".active");
                currentActive.classList.remove("active");
                link.classList.add("active");
            });

            pagination.appendChild(link);
        }
    }

    showItems(currentPage);
    setupPagination();
}

window.onload = function () {
    const pluginsDiv = document.getElementsByClassName("plugins");
    const plugins = pluginsDiv[0].getElementsByClassName("plugin")
    const max = 25;
    paginate(Array.from(plugins), max, ".pagination-container")
}