const queryInputFieldId = "query";
const mobileQueryInputFieldId = "mobilequery";
const allPluginsContainerDivClass = "allplugins";
const pluginContainerDivClassName = "plugin";
const allPluginsHeadingLabelClassName = "allpluginslabel";
const searchResultsDivClassName = "searchresults";
const searchResultsHeadingLabelClassName = "searchresultslabel";
const searchResultsLabelSelector = "h3." + searchResultsHeadingLabelClassName;
const gitHubStarsSelector = "div.githubstar";
const noresultsDivClassName = "noresults";

const allPlugins = [];
const elementsClassNames = [allPluginsContainerDivClass, allPluginsHeadingLabelClassName];

window.addEventListener("load", (event) => {
    const elements = document.querySelectorAll("div." + allPluginsContainerDivClass + " ul > li.plugin");
    for (let i = 0; i <= elements.length - 1; i++) {
        const element = elements[i];
        const name = element.getElementsByClassName('name');
        const desc = element.getElementsByClassName('desc')[0]?.textContent;
        const owner = element.getElementsByClassName('owner');
        const labels = element.getElementsByClassName('label');
        const vcsUrl = element.querySelector("h3.name > a").href
        const metaInfo = element.querySelector("p")?.outerHTML
        const ghStar = element.querySelector(gitHubStarsSelector)?.outerHTML

        const plugin = {
            name: name[0]?.textContent,
            desc: desc,
            owner: owner[0]?.textContent,
            labels: labelsAtPlugin(labels),
            vcsUrl: vcsUrl,
            metaInfo: metaInfo,
            ghStar: ghStar
        };

        allPlugins.push(plugin);
    }

    if (document.getElementById(queryInputFieldId)) {
        const e = document.getElementById(queryInputFieldId);
        e.oninput = onQueryChanged;
        e.onpropertychange = e.oninput; // for IE8
    }
    if (document.getElementById(mobileQueryInputFieldId)) {
        const e = document.getElementById(mobileQueryInputFieldId);
        e.oninput = onQueryChanged;
        e.onpropertychange = e.oninput; // for IE8
    }
});

function hideElementsToDisplaySearchResults() {
    for (let i = 0; i < elementsClassNames.length; i++) {
        const className = elementsClassNames[i];
        hideElementsByClassName(className);
    }
}

function resetDefault() {
    hideElementsByClassName(noresultsDivClassName)
    hideElementsByClassName(searchResultsDivClassName)
    hideElementsByClassName(searchResultsHeadingLabelClassName)
    searchResultsDiv.innerHTML = ""
    for (let i = 0; i < elementsClassNames.length; i++) {
        const className = elementsClassNames[i];
        showElementsByClassName(className);
    }
    paginate(defaultPluginList, max, pluginsContainer, paginationContainerClass);
}

function hideElementsByClassName(className) {
    const elements = document.getElementsByClassName(className);
    for (let i = 0; i < elements.length; i++) {
        const element = elements[i];
        element.classList.add("hidden");
    }
}

function showElementsByClassName(className) {
    const elements = document.getElementsByClassName(className);
    for (let i = 0; i < elements.length; i++) {
        const element = elements[i];
        element.classList.remove("hidden");
    }
}

function labelsAtPlugin(element) {

    const labels = [];
    for (let y = 0; y < element.length; y++) {
        labels.push(element[y].textContent)
    }
    return labels;
}

function onQueryChanged() {
    let query = queryValue()?.trim();
    const matchingPlugins = [];
    if (query === null || query === "") {
        resetDefault();
        return;
    } else if (query.length < 3) {
        return;
    }

    if (query !== '') {
        for (let i = 0; i <= allPlugins.length - 1; i++) {
            const plugin = allPlugins[i];
            if (doesPluginMatchesQuery(plugin, query)) {
                matchingPlugins.push(plugin);
            }
        }
    }
    if (searchResultsDiv) {
        if (matchingPlugins.length > 0) {
            if (searchResultsDiv.parentNode.getElementsByClassName(searchResultsLabelSelector).length === 0) {
                const searchResultHeadingLabel = document.querySelector(searchResultsLabelSelector);
                const querySpan = searchResultHeadingLabel.querySelector("span");
                querySpan.innerHTML = queryValue()
                showElementsByClassName(searchResultsHeadingLabelClassName)
            }
            hideElementsToDisplaySearchResults();
            searchResultsDiv.innerHTML = renderPlugins(matchingPlugins);
            paginate(Array.from(searchResultsDiv.getElementsByClassName(pluginContainerDivClassName)), max, searchResultsDiv, paginationContainerClass)
            showElementsByClassName(searchResultsDivClassName)
            hideElementsByClassName(noresultsDivClassName)
        } else if (matchingPlugins.length === 0) {
            hideElementsToDisplaySearchResults();
            showElementsByClassName(noresultsDivClassName)
            const pagination = document.querySelector(paginationContainerClass);
            pagination.innerHTML = "";
        }
    }
}

function doesTagsMatchesQuery(tags, query) {
    for (let x = 0; x < tags.length; x++) {
        const tag = tags[x];
        if (tag.toLowerCase().indexOf(query.toLowerCase()) !== -1) {
            return true;
        }
    }
    return false;
}

function doesTitleMatchesQuery(title, query) {
    if (title !== undefined) {
        if (title.toLowerCase().includes(query.toLowerCase())) {
            return true;
        }
    }
}

function doesOwnerMatchesQuery(owner, query) {
    if (owner !== undefined) {
        if (owner.toLowerCase().indexOf(query.toLowerCase()) !== -1) {
            return true;
        }
    }
}

function doesPluginMatchesQuery(guide, query) {
    return doesTitleMatchesQuery(guide.name, query) || doesOwnerMatchesQuery(guide.owner, query) || doesTagsMatchesQuery(guide.labels, query);
}

function queryValue() {
    let value = document.getElementById(queryInputFieldId).value;
    value = value.trim();
    if (value === '') {
        if (document.getElementById(mobileQueryInputFieldId)) {
            value = document.getElementById(mobileQueryInputFieldId).value;
            value = value.trim();
            return value;
        }
    }
    return value;
}

function renderPlugins(plugins) {
    let html = "";
    html += "  <ul>";
    for (let i = 0; i <= plugins.length - 1; i++) {
        html += "    " + renderPluginAsHtmlLi(plugins[i]);
    }
    html += "  </ul>";

    return html;
}

function renderPluginAsHtmlLi(plugin) {
    let html = "<li class='plugin'>";
    html += "<h3 class='name'><a href=" + plugin.vcsUrl + "\">" + plugin.name + "</a> </h3>";
    html += plugin.metaInfo;
    html += "<div class=\"owner\"><a href=/plugins/owners/" + plugin.owner + ".html>" + plugin.owner + "</a></div>";
    html += "<ul class='labels'>";
    for (let i = 0; i < plugin.labels.length; i++) {
        const label = plugin.labels[i]?.trim();
        html += "<li class='label'>";
        html += "<a href=\"/plugins/tags/" + label + ".html\">" + label + "</a>";
        html += "</li>"
    }
    html += "</ul>"
    html += plugin.ghStar;
    html += "</li>"
    return html;
}

