---
title: Git Commands
keywords: git command, git open source
last_updated: March 30, 2018
created : March 30, 2018
tags: [configuration]
summary: "Frequently used git commands for open source"
sidebar: mydoc_sidebar
permalink: git_commands.html
folder: configuration
---

{{site.url}}{{site.baseurl}}{{page.url}}

# Commands

## Force pull to overwrite local files

````
git fetch --all
git reset --hard origin/master
````

## Interactive Rebase 

### fix commits
````
git rebase -i HEAD~2
````

### rebase on working branch



## Creating a new branch based on upstream

To create a new branch with latest version of upstream.

````
git fetch upstream
git checkout upstream/master
git checkout -b <new-branch-name>
````

## Creating a new branch based on upstream's pull request

To create a new branch with latest version of upstream's pull request.

````
git fetch upstream pull/<pull-request-number>/head:<new-branch-name>
git checkout <new-branch-name>
````

## Force push from local to origin

From local repository force push to origin 

````
git push -f origin <branch-name>
````




{% include links.html %}
