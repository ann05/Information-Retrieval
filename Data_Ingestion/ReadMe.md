
1. Tweets have been collected using Twitter API and Python streaming code for the topics, Game of Thrones, US Elections, Korean War, US Open and Apple Event.
2. Solr is installed in an EC2 linux instance and a standalone server is started.
3. The tweets file is copied to this machine, into the core that is created for this project.
4. The data is indexed using the command bin/post -c core_name path
5. Data is indexed in solr, which is verified through Solr Admin.
