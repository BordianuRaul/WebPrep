<?php

namespace Utils;

use PDO;
use PDOException;

class DatabaseConnection
{
    private $host = "127.0.0.1";

    private $database = "subscribers";

    private $username = "root";

    private $password = "";

    private $charset = 'utf8';

    private $pdo;

    private $error;

    public function __construct()
    {
        $dataSourceName = "mysql:host=$this->host;dbname=$this->database;charset=$this->charset";

        $options = array(PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION,
            PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
            PDO::ATTR_EMULATE_PREPARES   => false);

        try {
            $this->pdo = new PDO($dataSourceName, $this->username, $this->password, $options);
        }
        catch(PDOException $e){
            $this->error = $e->getMessage();
            echo "Error connecting to DB: " . $this->error;
        }
    }

    public function getConnection() {
        return $this->pdo;
    }

    public function getError() {
        return $this->error;
    }

}