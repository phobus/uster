(function (window, document, uster, undefined) {

    'use strict';

    var CLASS_SELECT_TABLE = 'select-table';

    var SELECTOR_ROWS = 'tbody tr';

    var defaults = {
        path: ''
    };

    var SelectTable = function (el, config) {
        var node = document.querySelector(el);
        var settings = Object.assign({}, defaults, config);

        node.classList.add(CLASS_SELECT_TABLE);

        var rows = node.querySelectorAll(SELECTOR_ROWS);
        for (var i = 0; i < rows.length; ++i) {
            rows[i].addEventListener('click', function (event) {
                onRowClick(this, event, settings.path);
            });
        }
    };

    uster.SelectTable = SelectTable;

    function onRowClick(target, event, path) {
        event.stopPropagation();
        var dataset = getDatasetFromRow(target);
        if (dataset && dataset.id) {
            uster.post(path, {id: dataset.id});
        }
    }

    function getDatasetFromRow(target) {
        return target.dataset;
    }


})(window, document, window.uster = window.uster || {})
