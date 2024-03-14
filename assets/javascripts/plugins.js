let pluginsDiv;
let defaultPluginList;
let pluginsContainer;
let queryInputOffsetTop = document.getElementById("query")
let searchResultsDiv

window.addEventListener("load", (event) => {
    searchResultsDiv = document.querySelector("div.searchresults")
    pluginsDiv = document.getElementsByClassName("allplugins");
    pluginsContainer = document.querySelector("div.plugins");
    if (queryInputOffsetTop  !== null){
        queryInputOffsetTop = queryInputOffsetTop.offsetTop;
    }
    if (pluginsDiv.length > 0 && pluginsDiv[0].display !== "none") {
        defaultPluginList = Array.from(pluginsDiv[0].getElementsByClassName("plugin")).slice();
        paginate(defaultPluginList, max, pluginsContainer, paginationContainerClass);
    }
})
