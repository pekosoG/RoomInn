var express = require('express');
var router = express.Router();
let models = require('../db/models');
let params = require('strong-params');
let bodyparser = require('body-parser');

router.use(bodyparser.urlencoded({extended:true}));
router.use(bodyparser.json());
router.use(params.expressMiddleware());

router.get('/',function(req, res, next){
    models.House.findAll().then(function(resp){
        res.send(resp);
    });
});

router.get('/:id',function(req, res, next){
    let houseId=req.params.id;
    models.House.findOne({where:{id:houseId}}).then(function(resp){
        res.status(200).send({house:resp});
    });
});

router.post('/',function(req, res, next){
    let params = req.parameters;
    let houseParams = params.require('house').permit('name','address','photo','owner').value();
    models.Roomie.findOne({where:{id:houseParams.owner}}).then(function(resp){
        if(!resp){res.status(400).send({error:'Owner Not Found'})}
        models.House.create(houseParams).then(function(resp){
            res.status(201).send({house:resp});
        }).catch(function(err){
            res.status(400).send({err:err.message});
        });
    }).catch(function(err){
        res.status(400).send({err:err.message});
    });    
});

router.put('/:id',function(req, res, next){
    let params = req.parameters;
    let houseParams = params.require('house').permit('name','address','photo','owner').value();
    let houseId=req.params.id;

    models.House.findOne({where:{id:houseId}}).then(function(house){
        if(!house){ res.status(404).send({error:'House not found'})}
        models.House.update(houseParams,{where:{id:houseId}}).then(function(updatedHouse){
            res.status(201).send({house:houseParams});
        }).catch(function(err){
            res.status(400).send({err:err.message});
        });
    });
});

module.exports = router;