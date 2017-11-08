'use strict';
module.exports = (sequelize, DataTypes) => {
  var Roomie = sequelize.define('Roomie', {
    name: DataTypes.STRING,
    email: DataTypes.STRING,
    phone: DataTypes.STRING,
    photo: DataTypes.STRING,
    password: DataTypes.STRING,
    house_id: DataTypes.INTEGER,
    register_date: DataTypes.DATE,
    update_date: DataTypes.DATE
  }, {
    classMethods: {
      associate: function(models) {
        // associations can be defined here
      }
    }
  });
  return Roomie;
};