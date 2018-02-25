---
title: New project to Github
keywords: create new repository, initialize git locally, local project to github, local to git, new git, setting git 
last_updated: January 14, 2018
tags: [programming]
summary: "Making a new project and pushing to Github"
sidebar: mydoc_sidebar
permalink: new_project_to_git.html
folder: programming
---

Recently, I've started a small project on my local MAC and wanted to upload it to Github. 
(I'm using MAC with IntelliJ)

To do this, I needed to 
 - Make a new repository on Github
 - Setting .gitignore file
 - Initialize git on my local project 

## Make a new repository on Github
 - Click [new repository](https://github.com/new) and create a repository from Github
 - You should **NOT** check the 'Initialize this repository with a README' checkbox
    (If you have. It will get nasty when pushing the local repository to Github. We will take care of this at the end of this post)
  
![](../images/pages/new_project_to_git/Create a New Repository.png)


## Setting .gitignore file
It's important to set .gitignore files before pushing your project to Github or you will have sensitive information(password, etc) online.
Especially for Github, since it's open to everyone. Once your sensitive information is online, it should be considered compromised.
Even if you delete the files right away. Git holds the history of the file and they are still able to be opened from others.

To completely delete the history of such information. Please [check here.](https://help.github.com/articles/removing-sensitive-data-from-a-repository/)

Usually, a .gitignore file contains such list as below. Below is just an example. You should carefully check which are needed and which are not.
Here you can check the [Git ignore Document.](https://git-scm.com/docs/gitignore)

```
/.project
/.settings/
/.idea
/deploy
/target
/build/
/**/.idea
*.iml
/logs/*
```

## Initializing git on my local project
   
I've done this from the terminal in IntelliJ. You can also take care of this from any terminal, just be sure to start on the project's directory.

1. Initializing the project as Git repository.
````
$ git init
````

2. Add new files (adding new files in local repository to stage, being ready for the commit) 
````
$ git add .
or
$ git add File-Name
````

   - 2-1. **IF** you made mistake at step2. and want to unstage the files
````
$ git reset
or
$ git reset File-Name
````

3. Commit all staged files
````
$ git commit
````

4. Set remote path and check 
````
$ git remote add origin your new repository URL on Github
$ git remote -v
````

5. Push your project code to Github
````
$ git push -u origin master
````

   - 5-1.  **IF** you made a README.md when creating the repository on Github. you will get
````
fatal: refusing to merge unrelated histories
````
in this case, you need to do a pull request first with below command and then push once again.
````
$ git pull origin master --allow-unrelated-histories
````

and now your project has been uploaded to GitHub. Have fun.

{% include links.html %}
