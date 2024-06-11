<?php

class Channel
{
    public $id;
    public $ownerId;
    public $name;
    public $description;
    public $subscribers;

    public function _construct($id, $ownerId, $name, $description, $subscribers)
    {
        $this->id = $id;
        $this->ownerId = $ownerId;
        $this->name = $name;
        $this->description = $description;
        $this->subscribers = $subscribers;
    }


}