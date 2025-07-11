# Sporty Group Service Feed

# Local Development
### Prerequisites

* Install Docker: `brew install --cask docker`
* Install openjdk version "17.0.10": `brew cask install java`
* Set JAVA_HOME to java 17 installation directory (ex: /Library/Java/JavaVirtualMachines/corretto-17.0.10/Contents/Home)
        
### Starting the Application
* Run `mvn -U clean package`
* Build the image 
* $ docker build -t sporty-group-feed-img .
* Run the container
* $ docker run -p 8080:8080 sporty-group-feed-img
* Local swagger URL is available at http://localhost:8080/swagger-ui.html

### Two endpoints are available:
#### Process the feed for Alpha provider
* `curl --location 'http://localhost:8080/v1/provider-alpha/feed' \
--header 'Content-Type: application/json' \
--data '
{
    "msg_type": "settlement",
    "event_id": "ev123",
    "outcome": "1"
}'`
#### Process the feed for Beta provider
* `curl --location 'http://localhost:8080/v1/provider-beta/feed' \
--header 'Content-Type: application/json' \
--data '{
    "msg_type": "ODDS",
    "event_id": "ev456",
    "values": {
        "home": 2.0,
        "draw": 3.1,
        "away": 3.8
    }
}
'`

### Debugging
* Logs can be tailed by running `./docker/logs.sh`
