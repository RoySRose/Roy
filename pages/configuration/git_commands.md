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

From origin's branch to working branch.

````
git pull --rebase origin <branch-name>
````

### continue rebase
````
git rebase --continue
````

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

From local repository force push to origin.

````
git push -f origin <branch-name>
````

# Using git with SourceTree

1. SourceTree : Clone from URL
2. Create a new branch with upstream(origin)'s master 
   - SourceTree : Remote > Upstream(Origin) > rightclick on master and checkout
   - Git : [Creating a new branch based on upstream](https://roysrose.github.io/roy/git_commands.html#creating-a-new-branch-based-on-upstream)
3. Start programming
4. Commit or Stash the creation
5. Rebase the upstream(origin)'s master to your working branch
    - SourceTree : Pull menu > check 'Rebase instead of merge' check box > start pulling
    - Git : [rebase on working branch](https://roysrose.github.io/roy/git_commands.html#rebase-on-working-branch)
6. If conflict, fix problem by resolving conflict and
    - SourceTree : Actions menu > continue
    - Git : [continue rebase](https://roysrose.github.io/roy/git_commands.html#continue-rebase)
7. Push Branch to upstream(origin)
    - SourceTree : Push menu > start pushing
    - Git : [Force push from local to origin](https://roysrose.github.io/roy/git_commands.html#force-push-from-local-to-origin)(remove -f for without force)
8. Merge to Master from Github
9. Start again from 2 by creating a new branch with upstream(origin)'s master.




{% include links.html %}
