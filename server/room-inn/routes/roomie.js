var express = require('express');
var router = express.Router();
let models = require('../db/models');
let params = require('strong-params');
let bodyparser = require('body-parser');
let jwt = require('jsonwebtoken');
let superSecret = 'parangiricutirimicuaro*2';

router.use(bodyparser.urlencoded({extended:true}));
router.use(bodyparser.json());
router.use(params.expressMiddleware());

router.post('/',function(req, res, next){
    let params = req.parameters;
    let roomieParam = params.require('roomie').permit('name','email','phone','photo','password','house_id').value();
    models.Roomie.findOne({where:{email:roomieParam.email}}).then(function(resultRoomie){
        if(!resultRoomie){
            models.Roomie.create(roomieParam).then(function(resp){
                res.status(201).send({roomie:resp});
            }).catch(function(err){
                res.status(400).send({err:err.message});
            });
        }
        else
            res.status(400).send({err:'Email already in use'});
    });
});

router.use(function(req, res, next){
    var token = req.body.token || req.query.token || req.headers['x-access-token'];
    if (token) {
      jwt.verify(token, superSecret, function(err, decoded) {      
        if (err) {
          return res.json({ success: false, message: 'Failed to authenticate token.' });    
        } else {
          req.decoded = decoded;    
          next();
        }
      });
    } 
    else {
      return res.status(403).send({ 
        success: false, 
        message: 'No token provided.' 
      });
    }  
  });

router.get('/',function(req, res, next){
    models.Roomie.findAll().then(function(resp){
        res.send(resp);
    });
});

router.get('/:id',function(req, res, next){
    let roomieId=req.params.id;
    models.Roomie.findOne({where:{id:roomieId}}).then(function(resp){
        res.status(200).send({roomie:resp});
    });
});

router.get('/byHouse/:id',function(req, res, next){
    let houseId=req.params.id;
    models.Roomie.findAll({where:{house_id:houseId}}).then(function(resp){
        res.send(resp);
    });
});

router.put('/:id',function(req, res, next){
    let params = req.parameters;
    let roomieParam = params.require('roomie').permit('name','email','phone','photo','password','house_id').value();
    let roomieId=req.params.id;

    models.Roomie.findOne({where:{id:roomieId}}).then(function(roomie){
        if(!roomie){ res.status(404).send({error:'Roomie not found'})}
        models.Roomie.update(roomieParam,{where:{id:roomieId}}).then(function(updatedRoomie){
            res.status(201).send({roomie:roomieParam});
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

module.exports = router;