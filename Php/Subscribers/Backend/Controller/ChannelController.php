<?php

namespace Controller;

header("Access-Control-Allow-Origin: *");

require_once 'D:\xamp\htdocs\Subscribers\Backend\Repository\ChannelRepository.php';
class ChannelController
{
    private ChannelRepository $channelRepository;

    public function __construct(){
        $this->channelRepository = new ChannelRepository();
        $this->handleRequests();
    }

    public function handleRequests() {
        if(isset($_GET["ownerId"])){
            $ownerId = $_GET["ownerId"];
            $this->getAllChannelsForOwner($ownerId);
        }
    }

    public function getAllChannelsForOwner($ownerId){
        $channels = $this->channelRepository->getAllChannelsFromOwner($ownerId);
        header('Content-Type: application/json');
        echo json_encode($channels);
    }
}

$controller = new ChannelController();