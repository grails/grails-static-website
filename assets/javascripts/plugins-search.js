var queryInputFieldId = "query";
var mobileQueryInputFieldId = "mobilequery";
var allGuides = new Array();
var guideClassName = "plugin";
var multiguideClassName = "multiguide";


var elementsClassNames = new Array();
elementsClassNames.push('plugins');
elementsClassNames.push('tagcloud');
elementsClassNames.push('labels');
elementsClassNames.push('columnheader');
elementsClassNames.push('tagsbytopic');
elementsClassNames.push('plugin');

onload = function () {
    var elements = document.getElementsByClassName(guideClassName);
    for ( var i = 0; i < elements.length; i++ ) {
        var element = elements[i];
        var name = element.getElementsByClassName('name')
        var desc = element.getElementsByClassName('desc')
        var owner = element.getElementsByClassName('owner')
        var labels = element.getElementsByClassName('label')

        var description = desc[0].textContent

        var guide = {desc: description, name: name[0].textContent, owner: owner[0].textContent,labels: labelsAtPlugin(labels) }; /* */

        allGuides.push(guide);
    }

    if (document.getElementById(queryInputFieldId)) {
        var e = document.getElementById(queryInputFieldId);
        e.oninput = onQueryChanged;
        e.onpropertychange = e.oninput; // for IE8
    }
    if ( document.getElementById(mobileQueryInputFieldId) ) {
        var e = document.getElementById(mobileQueryInputFieldId);
        e.oninput = onQueryChanged;
        e.onpropertychange = e.oninput; // for IE8
    }
}

function hideElementsToDisplaySearchResults() {
    for ( var i = 0; i < elementsClassNames.length; i++) {
        var className = elementsClassNames[i];
        hideElementsByClassName(className);
    }
}

function showElementsToDisplaySearchResults() {
    for (var i = 0; i < elementsClassNames.length; i++) {
        var className = elementsClassNames[i];
        showElementsByClassName(className);
    }
}

function hideElementsByClassName(className) {
    var elements = document.getElementsByClassName(className);
    for ( var i = 0; i < elements.length; i++ ) {
        var element = elements[i];
        element.style.display = "none";
    }
}

function showElementsByClassName(className) {
    var elements = document.getElementsByClassName(className);
    for ( var i = 0; i < elements.length; i++ ) {
        var element = elements[i];
        element.style.display = "block";
    }
}

function labelsAtPlugin(element) {

    var labels = [];
    for (var y=0; y< element.length; y++){
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
    var query = queryValue();
    query = query.trim();
    if ( query === '' ) {
        showElementsToDisplaySearchResults();
        document.getElementById("searchresults").innerHTML = "";
        return;
    }

    var matchingGuides = [];
    if ( query !== '' ) {
        for (var i = 0; i < allGuides.length; i++) {
            var guide = allGuides[i];
            if ( doesGuideMatchesQuery(guide, query) ) {
                matchingGuides.push(guide);
            }
        }
    }
    if ( matchingGuides.length > 0 ) {
        hideElementsToDisplaySearchResults();
        var html = renderGuideGroup(matchingGuides, query);
        document.getElementById("searchresults").innerHTML = html;

    } else {
        document.getElementById("searchresults").innerHTML = "<div class='guidegroup'><div class='guidegroupheader'><h2>No results found</h2></div></div>";
    }
}

function doesTagsMatchesQuery(tags, query) {
    for ( var x = 0; x < tags.length; x++) {
        var tag = tags[x];
        if (tag.toLowerCase().indexOf(query.toLowerCase()) !== -1) {
            return true;
        }
    }
    return false;
}

function doesTitleMatchesQuery(title, query) {
    if (title !== undefined ){
        if (title.toLowerCase().indexOf(query.toLowerCase()) !== -1) {
            return true;
        }
    }
}

function doesOwnerMatchesQuery(owner, query) {
    if (owner !== undefined ){
        if (owner.toLowerCase().indexOf(query.toLowerCase()) !== -1) {
            return true;
        }
    }
}

function doesGuideMatchesQuery(guide, query) {
    if (doesTitleMatchesQuery(guide.name, query)) {
        return true;
    }
    if(doesOwnerMatchesQuery(guide.owner, query)){
        return true;
    }
    if(doesTagsMatchesQuery(guide.labels, query)) {
            return true;
        }

    return false;
}

function queryValue() {
    var value = document.getElementById(queryInputFieldId).value;
    value = value.trim();
    if ( value === '' ) {
        if (document.getElementById(mobileQueryInputFieldId)) {
            value = document.getElementById(mobileQueryInputFieldId).value;
            value = value.trim();
            return value;
        }
    }
    return value;
}

function renderGuideGroup(guides, query) {
    var html = "";

    html += "  <div class='guidegroupheader'>";
    html += "    <h2>Guides Filtered by: " + queryValue() + "</h2>";
    html += "  </div>";
    html += "  <ul>";
    for ( var i = 0; i < guides.length; i++ ) {
        html += "    " + renderGuideAsHtmlLi(guides[i], query);
    }
    html += "  </ul>";

    return html;
}

function renderGuideAsHtmlLi(guide, query) {
    var html = "<li class='plugin'>";
    html += "<h3 class='name'><a href=>" + guide.name + "</a> </h3>";
    html += "<p class='desc'>" + guide.desc + "</p>"
    html += "<a href=/plugins/owners/" + guide.owner +".html>"+guide.owner+ "</a>";

    html += "<ul class='labels'>";
        for (var i = 0; i < guide.labels.length; i++) {
            var label = guide.labels[i].trim()
            html += "<li class='label'>";
            html += "<a href=\"/plugins/tags/"+label+".html\">"+label+ "</a>";
            html += "</li>"
        }
    html += "</ul>"
    html += "</li>"
    return html;
}
