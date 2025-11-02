#!/bin/bash

# Script de test pour l'API REST avec support JSON et XML
# Usage: ./test_api.sh

BASE_URL="http://localhost:8082/banque/comptes"

echo "================================"
echo "Test de l'API REST - JSON et XML"
echo "================================"
echo ""

# 1. Test GET tous les comptes (JSON)
echo "1. GET /banque/comptes (JSON)"
curl -H "Accept: application/json" "$BASE_URL"
echo -e "\n"

# 2. Test GET tous les comptes (XML)
echo "2. GET /banque/comptes (XML)"
curl -H "Accept: application/xml" "$BASE_URL"
echo -e "\n"

# 3. Test GET un compte par ID (JSON)
echo "3. GET /banque/comptes/1 (JSON)"
curl -H "Accept: application/json" "$BASE_URL/1"
echo -e "\n"

# 4. Test GET un compte par ID (XML)
echo "4. GET /banque/comptes/1 (XML)"
curl -H "Accept: application/xml" "$BASE_URL/1"
echo -e "\n"

# 5. Test POST créer un compte (JSON)
echo "5. POST /banque/comptes (JSON)"
curl -X POST "$BASE_URL" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
    "solde": 10000.00,
    "dateCreation": "2025-10-27",
    "type": "EPARGNE"
  }'
echo -e "\n"

# 6. Test POST créer un compte (XML)
echo "6. POST /banque/comptes (XML)"
curl -X POST "$BASE_URL" \
  -H "Content-Type: application/xml" \
  -H "Accept: application/xml" \
  -d '<Compte>
    <solde>15000.00</solde>
    <dateCreation>2025-10-27</dateCreation>
    <type>COURANT</type>
  </Compte>'
echo -e "\n"

# 7. Test PUT mise à jour (JSON)
echo "7. PUT /banque/comptes/1 (JSON)"
curl -X PUT "$BASE_URL/1" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
    "solde": 8888.88,
    "dateCreation": "2025-10-27",
    "type": "COURANT"
  }'
echo -e "\n"

# 8. Test PUT mise à jour (XML)
echo "8. PUT /banque/comptes/2 (XML)"
curl -X PUT "$BASE_URL/2" \
  -H "Content-Type: application/xml" \
  -H "Accept: application/xml" \
  -d '<Compte>
    <solde>9999.99</solde>
    <dateCreation>2025-10-27</dateCreation>
    <type>EPARGNE</type>
  </Compte>'
echo -e "\n"

echo "================================"
echo "Tests terminés !"
echo "================================"

