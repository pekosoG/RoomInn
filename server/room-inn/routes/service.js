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

router.post('/',function(req, res, next){
    let params = req.parameters;
    let serviceParams = params.require('service').permit('name','icon','cost','payment_due','house_id').value();
    models.Service.create(serviceParams).then(function(resp){
        res.status(201).send({service:resp});
    }).catch(function(err){
        res.status(400).send({err:err.message});
    });
});

router.put('/',function(req, res, next){
    let params = req.parameters;
    let serviceParams = params.require('service').permit('name','icon','cost','payment_due','house_id').value();
    let serviceId=req.params.id;

    models.Service.findOne({where:{id:serviceId}}).then(function(service){
        if(!service){res.status(404).send({error:'Service not found'})}
        models.Service.update(serviceParams,{where:{id:serviceId}}).then(function(updatedService){
            res.status(201).send({service:updatedService});
        }).catch(function(err){
            res.status(400).send({err:err.message});
        });
    });
});

module.exports = router;