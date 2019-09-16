#!/bin/bash
# this script is for setting up my macbook for developing microservice-training at Accenture Interactive Mobility

echo "Setting up git global..."
sleep 1
git config --global user.name "Purba, Richard"
git config --global user.email "richard.purba@accenture.com"
git config --global http.proxy ''

git config --global -l
echo " "
echo "is it good???"