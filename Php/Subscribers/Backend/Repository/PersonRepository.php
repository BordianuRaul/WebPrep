<?php

namespace Repository;

use Utils\DatabaseConnection;

class PersonRepository
{
    private $pdo;

    public function __construct(){
        $databaseConnection = new DatabaseConnection();
        $this->pdo = $databaseConnection->getConnection();
    }
}