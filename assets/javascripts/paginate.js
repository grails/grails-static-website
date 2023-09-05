const paginationContainerClass = ".pagination-container";
const max = 18;
function paginate(items, itemsPerPage, itemsContainer, paginationContainer) {
    let currentPage = 1;
    const totalPages = Math.ceil(items.length / itemsPerPage);

    function showItems(page) {
        const startIndex = (page - 1) * itemsPerPage;
        const endIndex = startIndex + itemsPerPage;
        const pageItems = items.slice(startIndex, endIndex);

        itemsContainer.innerHTML = "";

        pageItems.forEach((item) => {
            const li = document.createElement("li");
            li.innerText = item.innerText;
            itemsContainer.appendChild(item);
        });
        window.scrollTo({top: queryInputOffsetTop, behavior: 'smooth'});
    }

    function setupPagination() {
        const pagination = document.querySelector(paginationContainer);
        pagination.innerHTML = "";

        if (totalPages > 1) {
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
    }

    showItems(currentPage);
    setupPagination();
}