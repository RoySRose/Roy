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


## Creating a new branch based on upstream

To create a new branch with latest version of upstream.

````
git fetch upstream
git checkout upstream/master
git checkout -b <new-branch-name>
````

## Force push from local to origin

From local repository force push to origin 

````
git push -f origin <branch-name>
````




{% include links.html %}
