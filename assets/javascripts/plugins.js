let pluginsDiv
let defaultPluginList;
let pluginsContainer;
let queryInputOffsetTop;
let searchResultsDiv

window.addEventListener("load", (event) => {
    searchResultsDiv = document.querySelector("div.searchresults")
    pluginsDiv = document.getElementsByClassName("allplugins");
    pluginsContainer = document.querySelector("div.plugins");
    queryInputOffsetTop = document.getElementById("query").offsetTop;
    if (pluginsDiv[0].display !== "none") {
        defaultPluginList = Array.from(pluginsDiv[0].getElementsByClassName("plugin")).slice();
        paginate(defaultPluginList, max, pluginsContainer, paginationContainerClass);
    }
})
