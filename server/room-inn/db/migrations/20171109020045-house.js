'use strict';

module.exports = {
  up: (queryInterface, Sequelize) => {
    return queryInterface.renameColumn('houses', 'addres', 'address');
  },
  down: (queryInterface, Sequelize) => {
  }
};
