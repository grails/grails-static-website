var queryInputFieldId = "query";
var mobileQueryInputFieldId = "mobilequery";
var allguides = new Array();
var guideClassName = "guide";
var multiguideClassName = "multiguide";


var elementsClassNames = new Array();
elementsClassNames.push('training');
elementsClassNames.push('latestguides');
elementsClassNames.push('guidegroup');
elementsClassNames.push('tagsbytopic');
elementsClassNames.push('guidesuggestion');

onload = function () {
    var elements = document.getElementsByClassName(guideClassName);
    for ( var i = 0; i < elements.length; i++ ) {
        var element = elements[i];
        var guide = { href: element.getAttribute('href'), title: element.text, tags: tagsAtGuide(element.parentNode)  }; /* */
        allguides.push(guide);
    }
    elements = document.getElementsByClassName(multiguideClassName);
    for ( var i = 0; i < elements.length; i++ ) {
        var element = elements[i];
        var guide = { title: titleAtMultiguide(element), languages: languagesAtMultiguide(element)  }; /* */
        allguides.push(guide);
    }

    if ( document.getElementById(queryInputFieldId) ) {
        var e = document.getElementById(queryInputFieldId);
        e.oninput = onQueryChanged;
        e.onpropertychange = e.oninput; // for IE8
    }
    if ( document.getElementById(mobileQueryInputFieldId) ) {
        var e = document.getElementById(mobileQueryInputFieldId);
        e.oninput = onQueryChanged;
        e.onpropertychange = e.oninput; // for IE8
    }
};

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

function titleAtMultiguide(element) {
    for (var y = 0; y < element.childNodes.length; y++) {
        if (element.childNodes[y].className == "title") {
            return element.childNodes[y].textContent;
        }
    }
    return '';
}

function languagesAtMultiguide(element) {
    var languages = [];

    for (var y = 0; y < element.childNodes.length; y++) {
        if (element.childNodes[y].className === "align-left") {
            var languageDiv = element.childNodes[y];
            var langEl;
            var hrefEl;
            var tagsArr = [];
            for (var x = 0; x < languageDiv.childNodes.length; x++) {
                if (languageDiv.childNodes[x].className === "lang") {
                    langEl = languageDiv.childNodes[x].textContent
                    hrefEl = languageDiv.childNodes[x].getAttribute('href');
                }
                if (languageDiv.childNodes[x].className === "tag") {
                    var tag = languageDiv.childNodes[x];
                    tagsArr.push(tag.textContent);
                }
            }
            languages.push({lang: langEl, href: hrefEl, tags: tagsArr})
        }
    }
    return languages
}

function tagsAtGuide(element) {
    var tags = new Array();

    for (var y = 0; y < element.childNodes.length; y++) {
        if (element.childNodes[y].className == "tag") {
            var tag = element.childNodes[y];
            tags.push(tag.textContent);
        }
    }
    return tags;
}

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
        for (var i = 0; i < allguides.length; i++) {
            var guide = allguides[i];
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
    if (title.toLowerCase().indexOf(query.toLowerCase()) !== -1) {
        return true;
    }
}

function doesGuideMatchesQuery(guide, query) {
    if (doesTitleMatchesQuery(guide.title, query)) {
        return true;
    }
    if (guide.tags === undefined || guide.tags === null) {
        for ( var i = 0; i < guide.languages.length; i++) {
            var language = guide.languages[i];
            if (doesTagsMatchesQuery(language.tags,query)) {
                return true;
            }
        }
    } else {
        if (doesTagsMatchesQuery(guide.tags, query)) {
            return true;
        }
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
    html += "<div class='guidegroup'>";
    html += "  <div class='guidegroupheader'>";
    html += "    <h2>Guides Filtered by: " + queryValue() + "</h2>";
    html += "  </div>";
    html += "  <ul>";
    for ( var i = 0; i < guides.length; i++ ) {
        html += "    " + renderGuideAsHtmlLi(guides[i], query);
    }
    html += "  </ul>";
    html += "</div>";
    return html;
}

function renderGuideAsHtmlLi(guide, query) {
    var html = "<li>";
    if (guide.tags === undefined || guide.tags === null) {
        html += "<div class=\"multiguide\">";
        html += "<span class=\"title\">" + guide.title + "</span>";
        var titleMatched = doesTitleMatchesQuery(guide.title, query);
        for (var i = 0; i < guide.languages.length; i++) {
            var language = guide.languages[i];
            var tagsMatched = doesTagsMatchesQuery(language.tags, query);
            if (titleMatched || tagsMatched) {
                html += "<div class=\"align-left\">";
                html += "<a class=\"lang\" href=\""+language.href+"\">"+language.lang+"</a>"
                for (var x = 0; x < language.tags.length; x++) {
                    var tag = language.tags[x];
                    html += "<span style='display: none' class='tag'>" + tag + "</span>";
                }
                html += "</div>";
            }
        }
        html += "</div>";
    } else {
        html += "<a class='" + guideClassName + "' href='" + guide.href + "'>" + guide.title + "</a>";
        for (var i = 0; i < guide.tags.length; i++) {
            var tag = guide.tags[i];
            html += "<span style='display: none' class='tag'>" + tag + "</span>";
        }
    }
    html += "</li>"
    return html;
}