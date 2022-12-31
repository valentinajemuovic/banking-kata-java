#/bin/zsh
# Creates an admin user to access Keycloak management console (http://localhost:10000/auth/admin)
# Example: ./src/main/resources/keycloak/create-admin-user.zsh admin admin

USERNAME=$1
PASSWORD=$2

docker exec keycloak /opt/jboss/keycloak/bin/add-user-keycloak.sh --user $USERNAME --password $PASSWORD
docker restart keycloak
