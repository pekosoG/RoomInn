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
    authenticate(req, res, next);
});

router.get('/',function(req, res, next){
    authenticate(req, res, next);
});

function authenticate(req, res, next){
    let params = req.parameters;
    let roomieData = params.require('roomie').permit('email','password').value();

    models.Roomie.findOne({where:{email:roomieData.email}}).then(function(roomie){
        if(!roomie){res.status(404).send({error:'Roomie not Found'});}
        if(roomie.password!=roomieData.password){
            res.status(400).send({error:'Incorrect Password'});
        }
        let payload={
            email: roomie.email,
            id: roomie.id
        }

        var token = jwt.sign(payload, superSecret, {
            expiresIn: 1440*10 // expires in 24 hours
        });

        res.status(200).send({status:true,token:token,roomie:roomie.id});
    });
}
module.exports = router;