'use strict';

module.exports = {
  up: (queryInterface, Sequelize) => {
    return queryInterface.addColumn(
      "services",
      "status",
      Sequelize.BOOLEAN 
  );
  },

  down: (queryInterface, Sequelize) => {
  }
};
