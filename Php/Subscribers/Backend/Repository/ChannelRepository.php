<?php

namespace Repository;

use Utils\DatabaseConnection;

class ChannelRepository
{
    private $pdo;

    public function __construct(){
        $databaseConnection = new DatabaseConnection();
        $this->pdo = $databaseConnection->getConnection();
    }

    public function getAllChannelsFromOwner($ownerId){
        $statement = $this->pdo->query("SELECT * FROM channels WHERE ownerId = " . $ownerId);
        return $statement->fetchAll(\PDO::FETCH_ASSOC);
    }
}