'use strict';
module.exports = (sequelize, DataTypes) => {
  var Invite = sequelize.define('Invite', {
    email: DataTypes.STRING,
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
  return Invite;
};