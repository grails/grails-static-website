/**
 * This layout accepts the following parameters:
 *
 * @param menu the navigation menu
 * @param pageTitle the page title
 * @param extraStyles , a list of CSS files to be added in the header
 * @param scripts , a list of scripts to be imported
 * @param iframeTarget , the URL of the page to be included as an iframe
 */
layout 'layouts/page.groovy', true,
        extraStyles: ['hide-overflow.css'],
        extraFooter: {
            script {
                yieldUnescaped '''
        (function() {
            var iframe = document.getElementsByTagName('iframe')[0];
            var iframeWindow = iframe.contentWindow;

            window.addEventListener('hashchange', function() {
                iframeWindow.postMessage({
                    type: 'hashchange',
                    hash: window.location.hash
                }, '*');
            });
            window.addEventListener('message', function(e) {
                window.location.hash = e.data.hash;
            });

            iframe.onload = function() {
                iframeWindow.postMessage({
                    type: 'init',
                    hash: window.location.hash
                }, '*');
            };
        })();
                '''
            }
        },
        mainContent: contents {
            div {
                include template: 'includes/topmenu.groovy'
            }
            iframe(class: 'doc-embed', frameborder: '0', height: '100%', width: '100%', style:'display: block;padding-bottom: 70px;', src: iframeTarget) {}
        }
