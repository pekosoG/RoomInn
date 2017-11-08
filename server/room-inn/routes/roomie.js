var express = require('express');
var router = express.Router();
let models = require('../db/models');
let params = require('strong-params');
let bodyparser = require('body-parser');

router.use(bodyparser.urlencoded({extended:true}));
router.use(bodyparser.json());
router.use(params.expressMiddleware());

router.get('/',function(req, res, next){
    models.Roomie.findAll().then(function(resp){
        res.send(resp);
    });
});

router.post('/',function(req, res, next){
    let params = req.parameters;
    let roomieParam = params.require('roomie').permit('name','email','phone','photo','password','house_id').value();
    models.Roomie.create(roomieParam).then(function(resp){
        res.status(201).send({roomie:resp});
    }).catch(function(err){
        res.status(400).send({err:err.message});
    });
});

router.put('/:id',function(req, res, next){
    let params = req.parameters;
    let roomieParam = params.require('roomie').permit('name','email','phone','photo','password','house_id').value();
    let roomieId=req.params.id;

    models.Roomie.findOne({where:{id:roomieId}}).then(function(roomie){
        if(!roomie){ res.status(404).send({error:'Roomie not found'})}
        models.Roomie.update(roomieParam,{where:{id:roomieId}}).then(function(updatedRoomie){
            res.status(201).send({roomie:updatedRoomie});
        }).catch(function(err){
            res.status(400).send({err:err.message});
        });
    });
});

router.post('/invite',function(req, res, next){
    let params = req.parameters;
    let inviteData = params.require('invite').permit('email','house_id').value();
    models.House.findOne({where:{id:inviteData.house_id}}).then(function(house){
        if(!house){ res.status(404).send({error:'House not found'})} 
        models.Invite.create(inviteData).then(function(invited){
            res.status(201).send({invited:invited});
        });
    }).catch(function(err){
        res.status(400).send({err:err.message});
    });
});

router.post('/authenticate',function(req, res, next){
    let params = req.parameters;
    let roomieData = params.require('roomie').permit('email','password').value();

    models.Roomie.findOne({where:{email:roomieData.email}}).then(function(roomie){
        if(!roomie){res.status(404).send({error:'Roomie not Found'});}
        if(roomie.password!=roomieData.password){
            res.status(400).send({error:'Incorrect Password'});
        }
    });
});

module.exports = router;