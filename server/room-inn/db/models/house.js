'use strict';
module.exports = (sequelize, DataTypes) => {
  var House = sequelize.define('House', {
    name: DataTypes.STRING,
    addres: DataTypes.STRING,
    photo: DataTypes.STRING,
    owner: DataTypes.INTEGER,
    register_date: DataTypes.DATE,
    update_date: DataTypes.DATE
  }, {
    classMethods: {
      associate: function(models) {
        // associations can be defined here
      }
    }
  });
  return House;
};