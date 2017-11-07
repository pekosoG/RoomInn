var express = require('express');
var router = express.Router();
let models = require('../db/models');
let params = require('strong-params');
let bodyparser = require('body-parser');

router.use(bodyparser.urlencoded({extended:true}));
router.use(bodyparser.json());
router.use(params.expressMiddleware());

router.get('/',function(req, res, next){
    models.Service.findAll().then(function(resp){
        res.send(resp);
    });
});

module.exports = router;