'use strict';
module.exports = (sequelize, DataTypes) => {
  var Service = sequelize.define('Service', {
    name: DataTypes.STRING,
    icon: DataTypes.STRING,
    cost: DataTypes.DOUBLE,
    payment_due: DataTypes.INTEGER,
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
  return Service;
};