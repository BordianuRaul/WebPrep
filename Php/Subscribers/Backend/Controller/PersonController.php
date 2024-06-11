<?php

namespace Controller;

use Repository\PersonRepository;

class PersonController
{
    private $personRepository;

    public function __construct(){
        $this->personRepository = new PersonRepository();
    }
}