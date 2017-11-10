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

router.get('/:id',function(req, res, next){
    let serviceId=req.params.id;
    models.Service.findOne({where:{id:serviceId},raw:true}).then(function(resp){
        models.Roomie.findAll({where:{house_id:resp.house_id}}).then(function(roomies){
            if(roomies)
                resp.individual_cost=resp.cost/roomies.length;
            else
                resp.individual_cost=resp.cost;
            res.status(200).send({service:resp});
        })
    });
});

router.get('/byHouse/:id',function(req, res, next){
    let houseId=req.params.id;
    models.Service.findAll({where:{house_id:houseId}}).then(function(resp){
        models.Roomie.findAll({where:{house_id:houseId}}).then(function(roomies){
            response=resp.map(function(item){
                if(roomies)
                    item.dataValues.individual_cost=item.cost/roomies.length;
                else
                    item.dataValues.individual_cost=item.cost;
                return item;
            });
            res.status(200).send(response);
        })
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

router.put('/:id',function(req, res, next){
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