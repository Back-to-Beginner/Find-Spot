import http from 'k6/http';
import { sleep } from 'k6';

export default function() {

    // http.get("http://localhost:8080/api/v1/images/compare?challengeUrl=https%3A%2F%2Ffindspot.s3.ap-northeast-2.amazonaws.com%2Fstatic%2Fe00ce85b-0004-45a7-8b85-207812860913.png&missionId=13"); // get request to input URI
    // sleep(1); // sleep while a second
    var id = Math.floor(Math.random() * 50) + 1;
    http.get(`http://localhost:8080/api/v1/posts/parent/${id}/child/s`)
    sleep(1); // sleep while a second
}