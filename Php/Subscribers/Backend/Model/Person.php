<?php

class Person
{
    public $id;
    public $name;
    public $age;
    public $gender;

    public function _construct($id, $name, $age, $gender)
    {
        $this->id = $id;
        $this->name = $name;
        $this->age = $age;
        $this->gender = $gender;
    }
}