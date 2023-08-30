const queryInputFieldId = "query";
const mobileQueryInputFieldId = "mobilequery";
const allGuides = [];
const guideClassName = "plugin";
const elementsClassNames = [];
elementsClassNames.push('plugins');
elementsClassNames.push('tagcloud');
elementsClassNames.push('labels');
elementsClassNames.push('columnheader');
elementsClassNames.push('tagsbytopic');
elementsClassNames.push('plugin');

onload = function () {
    const elements = document.getElementsByClassName(guideClassName);
    for (let i = 0; i < elements.length; i++) {
        const element = elements[i];
        const name = element.getElementsByClassName('name');
        const desc = element.getElementsByClassName('desc');
        const owner = element.getElementsByClassName('owner');
        const labels = element.getElementsByClassName('label');

        const description = desc[0]?.textContent;

        const guide = {
            desc: description,
            name: name[0]?.textContent,
            owner: owner[0]?.textContent,
            labels: labelsAtPlugin(labels)
        };

        allGuides.push(guide);
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
}

function hideElementsToDisplaySearchResults() {
    for (let i = 0; i < elementsClassNames.length; i++) {
        const className = elementsClassNames[i];
        hideElementsByClassName(className);
    }
}

function showElementsToDisplaySearchResults() {
    for (let i = 0; i < elementsClassNames.length; i++) {
        const className = elementsClassNames[i];
        showElementsByClassName(className);
    }
}

function hideElementsByClassName(className) {
    const elements = document.getElementsByClassName(className);
    for (let i = 0; i < elements.length; i++) {
        const element = elements[i];
        element.style.display = "none";
    }
}

function showElementsByClassName(className) {
    const elements = document.getElementsByClassName(className);
    for (let i = 0; i < elements.length; i++) {
        const element = elements[i];
        element.style.display = "block";
    }
}

function labelsAtPlugin(element) {

    const labels = [];
    for (let y = 0; y < element.length; y++) {
        labels.push(element[y].textContent)
    }
    return labels;
}


//    for (var y=0; y< element.childNodes.length; y++){
//    var label = element.childNodes[y].getElementsByClassName('labels');
//    var liElements = label.getElementsByTagName('li')
//        if (element.childNodes[y].className == "labels") {
//
//        //TODO GET LI FROM UL
//            for (var i=0;y <liElements.length;i ++){
//                labels.push(liElements[i])
//            }
//        }
//    }

function onQueryChanged() {
    let query = queryValue();
    query = query.trim();
    if (query === '') {
        showElementsToDisplaySearchResults();
        document.getElementById("searchresults").innerHTML = "";
        return;
    }

    const matchingGuides = [];
    if (query !== '') {
        for (let i = 0; i < allGuides.length; i++) {
            const guide = allGuides[i];
            if (doesGuideMatchesQuery(guide, query)) {
                matchingGuides.push(guide);
            }
        }
    }
    if (matchingGuides.length > 0) {
        hideElementsToDisplaySearchResults();
        document.getElementById("searchresults").innerHTML = renderGuideGroup(matchingGuides);

    } else {
        document.getElementById("searchresults").innerHTML = "<div class='guidegroup'><div class='guidegroupheader'><h2>No results found</h2></div></div>";
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

function doesGuideMatchesQuery(guide, query) {
    return doesTitleMatchesQuery(guide.name, query) ||
        doesOwnerMatchesQuery(guide.owner, query) ||
        doesTagsMatchesQuery(guide.labels, query);
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

function renderGuideGroup(guides) {
    let html = "";
    html += "  <h3 class=\"columnheader\">Plugins Filtered by: " + queryValue() + "</h3>";
    html += "  <ul>";
    for (let i = 0; i <= guides.length-1; i++) {
        html += "    " + renderGuideAsHtmlLi(guides[i]);
    }
    html += "  </ul>";

    return html;
}

function renderGuideAsHtmlLi(guide) {
    let html = "<li class='plugin'>";
    html += "<h3 class='name'><a href=>" + guide.name + "</a> </h3>";
    html += "<p class='desc'>" + guide.desc + "</p>"
    html += "<a href=/plugins/owners/" + guide.owner + ".html>" + guide.owner + "</a>";

    html += "<ul class='labels'>";
    for (let i = 0; i < guide.labels.length; i++) {
        const label = guide.labels[i]?.trim();
        html += "<li class='label'>";
        html += "<a href=\"/plugins/tags/" + label + ".html\">" + label + "</a>";
        html += "</li>"
    }
    html += "</ul>"
    html += "</li>"
    return html;
}
