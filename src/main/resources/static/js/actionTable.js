(function (window, document, uster, undefined) {

    'use strict';

    var CLASS_ACTION_TABLE = 'action-table';

    var SELECTOR_ROWS = 'tbody tr';
    var SELECTOR_ACTION_EDIT = '.action-edit';
    var SELECTOR_ACTION_DELETE = '.action-delete';

    var defaults = {
        path: ''
    };

    var ActionTable = function (el, config) {
        var node = document.querySelector(el);
        var settings = Object.assign({}, defaults, config);

        node.classList.add(CLASS_ACTION_TABLE);

        // row click show details
        var rows = node.querySelectorAll(SELECTOR_ROWS);
        for (var i = 0; i < rows.length; ++i) {
            rows[i].addEventListener('click', function (event) {
                onRowClick(this, event, settings.path);
            });
        }

        // buttons edit
        var editActions = node.querySelectorAll(SELECTOR_ACTION_EDIT);
        for (var i = 0; i < rows.length; ++i) {
            editActions[i].addEventListener('click', function (event) {
                onButtonEditClick(this, event, settings.path);
            });
        }

        // buttons delete
        var deleteActions = node.querySelectorAll(SELECTOR_ACTION_DELETE);
        for (var i = 0; i < rows.length; ++i) {
            deleteActions[i].addEventListener('click', function (event) {
                onButtonDeleteClick(this, event, settings.path);
            });
        }
    };

    uster.ActionTable = ActionTable;

    function onRowClick(target, event, path) {
        event.stopPropagation();
        var dataset = getDatasetFromRow(target);
        if (dataset && dataset.id) {
            window.location = path + '/' + dataset.id;
        }
    }

    function onButtonEditClick(target, event, path) {
        event.stopPropagation();
        var dataset = getDatasetFromButton(target);
        if (dataset && dataset.id) {
            window.location = path + '/' + dataset.id + '/edit';
        }
    }

    function onButtonDeleteClick(target, event, path) {
        event.stopPropagation();
        if (confirm('Are you sure you want to delete?')) {
            var dataset = getDatasetFromButton(target);
            if (dataset && dataset.id) {
                uster.post(path + '/' + dataset.id + '/delete', {});
            }
        }
    }

    function getDatasetFromRow(target) {
        return target.dataset;
    }

    function getDatasetFromButton(target) {
        var cell = target.parentNode;
        var row = cell.parentNode;
        return row.dataset;
    }

})(window, document, window.uster = window.uster || {})
