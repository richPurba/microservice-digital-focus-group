#!/bin/bash
# this script is for setting up my macbook for developing microservice-training at Accenture Interactive Mobility

echo "Setting up git global..."
sleep 1
git config --global user.name "Lastname, Firstname"
git config --global user.email "something@something.com"
git config --global http.proxy ''

git config --global -l
echo " "
echo "is it good???"
