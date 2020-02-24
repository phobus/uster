(function (window, document, uster, undefined) {
    'use strict';

    /**
     * sends a request to the specified url from a form. this will change the window location.
     * @param {string} path the path to send the post request to
     * @param {object} params the paramiters to add to the url
     * @param {string} [method=post] the method to use on the form
     */
    function post(path, params, method) {
        method = method || 'post';
        // The rest of this code assumes you are not using a library.
        // It can be made less wordy if you use one.
        var form = document.createElement('form');
        form.method = method;
        form.action = path;

        for (var key in params) {
            if (params.hasOwnProperty(key)) {
                var hiddenField = document.createElement('input');
                hiddenField.type = 'hidden';
                hiddenField.name = key;
                hiddenField.value = params[key];

                form.appendChild(hiddenField);
            }
        }

        document.body.appendChild(form);
        form.submit();
    }

    uster.post = post;
})(window, document, window.uster = window.uster || {})