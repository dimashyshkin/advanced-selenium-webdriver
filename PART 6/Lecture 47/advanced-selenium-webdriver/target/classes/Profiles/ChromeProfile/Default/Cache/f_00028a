/// <reference path="Agility.js" />

Agility.RegisterNamespace("Agility.UGC.API");

(function (API) {

	//ENUMS
	API.DataType = {
		String: 0,
		TextBlob: 1,
		Int: 2,
		Double: 3,
		DateTime: 4,
		File: 5,
		GUID: 6,
		Encrypted: 7,
		Hashed: 8,
		Boolean: 9,
		Dropdown: 10,
		HTML: 11,
		Email: 12
	}

	API.RecordState = {
		New: 0,
		Published: 1,
		Deleted: 2,
		Processing: 3,
		Declined: 4,
		Error: 5,
		Escalated: 6,
		AwaitingReview: 7,
		Draft: 8,
		All: -1
	}

	API.Permission = {
		Read: 0,
		Create: 1,
		Edit: 2,
		Delete: 3,
		Moderate: 4,
		Administer: 5
	}

	API.FileStorage = {
		RelativeURL: 0,
		BaseURL: 1,
		EdgeURL: 2
	}

	API.SortDirection = {
		ASC: "ASC",
		DESC: "DESC"
	}

	API.ResponseType = {
		OK: 0,
		Error: 1
	}

	API.FileService = {
		AmazonS3: 0,
		YouTube: 1,
		Flickr: 2,
		Image: 3,
		Video: 4,
		Ooyala: 5,
		Brightcove: 6
	}

	API.FileServiceState = {
		New: 0,
		Unprocessed: 1,
		Processed: 2,
		Error: 3
	}

	API.RecordFlag = {
		Spam: "Spam",
		Flagged: "Flagged",
		Illegal: "Illegal",
		Abuse: "Abuse"
	}

	API.AggregateTypes = {
		Count: 0,
		Sum: 1,
		Average: 2
	}

	API.FeedbackContentType = {
		WCM: 0,
		UGC: 1,
		Custom: 2
	}

	API.FeedbackSubmissionType = {
		Like: 0,
		Rating: 1,
		Vote: 2,
		ApproveDisapprove: 3,
		Custom: 4
	}

	API.SubmissionInterval = {
		None: -1,
		Day: 0,
		Hour: 1,
		Week: 2,
		Month: 3,
		Minute: 4,
		SingleSubmissionOnly: 5
	}


	API.FileServiceMetaDataTypeName = "JSONPFileServiceMetaData:#Agility.UGC.API.ServiceObjects";

	API.YouTubeMetaField = {
		OriginalFilePath: "OriginalFilePath",
		VideoID: "VideoID",
		Description: "Description",
		Category: "Category",
		Title: "Title",
		Keywords: "Keywords",
		Private: "Private",
		YouTubeState: "YouTubeState",
		Duration: "Duration"
	};

	//from here: http://www.flickr.com/services/api/upload.api.html

	API.FlickrMetaField = {
		OriginalFilePath: "OriginalFilePath",
		title: "title",
		description: "description",
		tags: "tags",
		is_public: "is_public",
		is_friend: "is_friend",
		is_family: "is_family",
		safety_level: "safety_level",
		hidden: "hidden",
		photo_id: "photo_id"

		//other values are also appended as they are made available 
		/*
		secret
		photopage
		SquareImageUrl
		ThumbnailImageUrl
		SmallImageUrl
		MediumImageUrl
		LargeImageUrl
		*/
	};

	API.OoyalaMetaField = {
		embedCode: "embedCode",
		title: "title",
		description: "description",
		status: "status"

		//http://www.ooyala.com/support/docs/backlot_api#query
		//other metadata (plus any "generic" metadata) values are also appended as they are made available 
		/*
		thumbnailUrl
		hostedAt
		content_type
		uploadedAt
		length
		size
		updatedAt
		width
		height
		*/
	};

	API.SearchArg = function () {
		///	<summary>
		///	The search object for the SearchRecords method.
		///	</summary>
		///<field name="PageSize" Type="Number">The number of records per page.</field>
		///<field name="RecordOffset" Type="Number"></field>
		///<field name="IncludeSpam" Type="Boolean"></field>
		///<field name="IncludeFlagged" Type="Boolean"></field>
		///<field name="IncludeIllegal" Type="Boolean"></field>
		///<field name="State" Type="API.RecordState"></field>
		///<field name="SortedField" Type="String"></field>
		///<field name="SortDirection" Type="String"></field>
		///<field name="RecordTypeName" Type="String"></field>
		///<field name="Search" Type="String">The search string.</field>
		///<field name="CacheKey" Type="String">A custom cache key that will be used instead of the record type id to cache the results with.</field>
		///<field name="Columns" Type="Array">A list of the field names that you want to be returned.</field>

		PageSize = 20;
		RecordOffset = 0;
		IncludeSpam = false;
		IncludeFlagged = false;
		IncludeIllegal = false;
		State = API.RecordState.Published;
		SortedField = "";
		SortDirection = "";
		RecordTypeName = null;
		Search = "";
		CacheKey = null;
		Columns = [];
		FileStorage = API.FileStorage.RelativeURL;
		IncludeStatistics = false;
	}

	API.CommentSearchArg = function () {
		///	<summary>
		///	The search object for the SearchComments method.
		///	</summary>
		///<field name="RelatedContentID" Type="Number">The ID of the Related Content Item</field>
		///<field name="PageSize" Type="Number">The number of records per page.</field>
		///<field name="RecordOffset" Type="Number"></field>
		///<field name="SortedField" Type="String"></field>
		///<field name="SortDirection" Type="String"></field>
		///<field name="RecordTypeName" Type="String"></field>
		///<field name="CacheKey" Type="String">A custom cache key that will be used instead of the record type id to cache the results with.</field>
		///<field name="OtherStates" Type="Array"></field>
		///<field name="State" Type="API.RecordState"></field>
		///<field name="FileStorage" Type="API.FileStorage"></field>
		///<field name="Columns" Type="Array">A list of the field names that you want to be returned.</field>


		RelatedContentID: -1;
		PageSize: 20;
		RecordOffset: 0;
		SortedField: "CreatedOn";
		SortDirection: "DESC";
		RecordTypeName: "";
		CacheKey = null;
		OtherStates = [];
		State = API.RecordState.Published;
		FileStorage = API.FileStorage.RelativeURL;
		Columns = [];
		IgnoreAbuse = true;
		IncludeFlagged = false;
		IncludeIllegal = false;

	}

	API.FeedbackSearchArg = function () {
		///	<summary>
		///	The search object for the GetFeedbackAggregate method.
		///	</summary>
		///<field name="ReferenceName" Type="String">The Reference name of the list the feedback refers to</field>
		///<field name="FeedbackContentType" Type="FeedbackContentType">The content type the reference name refers to (WCM/UGC/Custom)</field>
		///<field name="FeedbackSubmissionType" Type="FeedbackSubmissionType">The submission type the reference name refers to (Like/Vote/Rating/ApproveDisaprove/Custom)</field>
		///<field name="RelatedContentID" Type="int">The ID the item is related to</field>
		///<field name="StartDate" Type="Date"></field>
		///<field name="EndDate" Type="Date"></field>
		///<field name="IsPositive" Type="Boolean"></field>
		///<field name="Action" Type="AggregateTypes">The action to perform (Count/Sum/Average)</field>

		ReferenceName = null;
		ContentType = -1;
		SubmissionType = -1;
		RelatedContentIDs = new Array();
		StartDate = null;
		EndDate = null;
		IsPositive = null;
		Action = null;

	}


	API.BooleanFeedback = function () {
		BooleanFeedbackID = -1;
		FeedbackTypeID = -1;
		RelatedContentID = -1;
		ProfileRecordID = -1;
		ExternalProfileID = null;
		CreatedOn = null;
		IsPositive = null;

		ReferenceName = null;
		ContentType = -1;
		SubmissionType = -1;

		//Submission Rules
		RequiresAuthentication = false;
		SubmissionIntervalUnit = API.SubmissionInterval.None;
		SubmissionIntervalValue = 1;
	}

	API.RatingFeedback = function () {
		RatingFeedbackID = -1;
		FeedbackTypeID = -1;
		RelatedContentID = -1;
		ProfileRecordID = -1;
		ExternalProfileID = null;
		CreatedOn = null;
		IsPositive = null;
		RatingValue = 0;

		ReferenceName = null;
		ContentType = -1;
		SubmissionType = -1;

		//Submission Rules
		RequiresAuthentication = false;
		SubmissionIntervalUnit = API.SubmissionInterval.None;
		SubmissionIntervalValue = 1;
	}

	API.Record = function () {
		var ID = -1;
		var RecordTypeName;
		var CreatedOn;
		var ModifiedOn;
	}

	API.Initialized = false;

	API.APIUrl = "";

	API.OnInit = function (API_Url, API_AccessKey, API_Seconds, API_RandomNumber, API_ProfileRecordID, API_Hash) {
		/// <summary>
		/// Initialize the Agility DataService API
		/// </summary>
		/// <param name="API_Url" type="String">The URL to the Agility DataService REST API.</param>        
		/// <param name="API_AccessKey" type="String">The API Key.</param>
		/// <param name="API_Seconds" type="String">The # of Seconds.</param>
		/// <param name="API_RandomNumber" type="String">A Random number.</param>        
		/// <param name="API_ProfileRecordID" type="String">The current user ID.</param>        
		/// <param name="API_Hash" type="String">The API Hash.</param>        

		if (API_Url != undefined && API_Url != null) {
			API.APIUrl = API_Url;
		}
		API.APIAccessKey = API_AccessKey;
		API.APISeconds = API_Seconds;
		API.APIRandomNumber = API_RandomNumber;
		API.APIProfileRecordID = API_ProfileRecordID;
		API.APIHash = API_Hash;
		API.Initialized = true;

	}


	function checkApiIntialized(callback) {

		if (API.APIUrl == null || API.APIUrl == "" || API.APIAccessKey == undefined) {
			callback({
				ResponseType: API.ResponseType.Error,
				Message: "The Agility UGC API has not been initialized.",
				ResponseData: null
			});

			return false;
		}

		return true;
	}



	API.GetAllRecordTypes = function (callback) {
		/// <summary>
		/// Get All Record Types.
		/// </summary>
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if (!checkApiIntialized(callback)) return;
		var url = _buildAPIUrl("GetAllRecordTypes", null);



		jQuery.getJSON(url, callback);

	}

	API.GetRecordType = function (recordTypeID, callback) {
		/// <summary>
		/// Get a record based on the record type id.
		/// </summary>
		/// <param name="recordTypeID">The ID of the record type to return.</param>      
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      
		if (!checkApiIntialized(callback)) return;
		var url = _buildAPIUrl("GetRecordType", [recordTypeID]);

		jQuery.getJSON(url, callback);

	}

	API.GetRecordTypeByName = function (recordTypeName, callback) {
		/// <summary>
		/// Get a record based on the record type name.
		/// </summary>
		/// <param name="recordTypeName">The name of the record type to return.</param>      
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      
		if (!checkApiIntialized(callback)) return;

		var url = _buildAPIUrl("GetRecordTypeByName", [recordTypeName]);
		jQuery.getJSON(url, callback);
	}

	API.SaveRecordType = function (recordType, callback) {
		/// <summary>
		/// Saves a record type.
		/// </summary>
		/// <param name="recordType">The record type to save.</param>      
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if (!checkApiIntialized(callback)) return;
		var postData = Agility.JSON.encode(recordType);

		var url = _buildAPIUrl("SaveRecordType", null);

		_submitPostData(url, postData, callback);

	}

	API.DeleteRecordType = function (recordTypeID, callback) {
		/// <summary>
		/// Get All Record Types.
		/// </summary>
		/// <param name="recordTypeID">The ID of the record type to return.</param>      
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if (!checkApiIntialized(callback)) return;

		var url = _buildAPIUrl("DeleteRecordType", [recordTypeID]);

		jQuery.getJSON(url, callback);

	}

	API.GetRecord = function (recordID, fileStorage, callback) {
		/// <summary>
		/// Gets a Record.
		/// </summary>
		/// <param name="recordID" type="Number">The ID of the record to return.</param>      
		/// <param name="RelativeURL" type="Agility.UGC.API.FileStorage">(optional) The method to be used for building file field URLs that may be on this document.</param>      
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if ($.isFunction(fileStorage)) {
			callback = fileStorage;
			fileStorage = null;

		}

		if (!checkApiIntialized(callback)) return;

		var url = null;
		if (fileStorage == null) {
			url = _buildAPIUrl("GetRecord", [recordID]);
		} else {
			url = _buildAPIUrl("GetRecord", [recordID], "fileStorage=" + escape(fileStorage));
		}

		jQuery.getJSON(url, callback);

	}

	API.GetRecordHistory = function (recordID, callback) {
		/// <summary>
		/// Gets a the Version Histor for a Record.  This method requires admin access.
		/// </summary>
		/// <param name="recordID">The ID of the record to return.</param>      
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if (!checkApiIntialized(callback)) return;

		var url = _buildAPIUrl("GetRecordHistory", [recordID]);

		jQuery.getJSON(url, callback);

	}



	API.DeleteRecord = function (recordID, callback) {
		/// <summary>
		/// Deletes a Record.
		/// </summary>
		/// <param name="recordID">The ID of the record to return.</param>      
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if (!checkApiIntialized(callback)) return;

		var url = _buildAPIUrl("DeleteRecord", [recordID]);

		jQuery.getJSON(url, callback);

	}


	API.SetRecordFlag = function (recordID, flag, reason, callback) {
		/// <summary>
		/// Sets whether a record is flagged or not.
		/// </summary>
		/// <param name="recordID" type="Number">The ID of the record to return.</param>      
		/// <param name="flag" type="Agility.UGC.API.RecordFlag">Whether the record is flagged or not.</param> 
		/// <param name="reason" type="String">Whether the record is flagged or not.</param> 		     		
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if (!checkApiIntialized(callback)) return;

		var query = null;
		if (reason != null && reason != "") {
			query = "reason=" + escape(reason);
		}

		var url = _buildAPIUrl("SetRecordFlag", [recordID, flag], query);

		jQuery.getJSON(url, callback);

	}


	API.InsertRecordHistory = function (recordID, comment, callback) {
		/// <summary>
		/// Adds a comment to the record history.
		/// </summary>
		/// <param name="recordID">The ID of the record to return.</param>		
		/// <param name="comment">The comment.</param>      			
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if (!checkApiIntialized(callback)) return;

		var postData = comment;

		var url = _buildAPIUrl("InsertRecordHistory", [recordID]);

		_submitPostData(url, postData, callback);


	}

	API.SetRecordState = function (recordID, recordState, reason, comment, callback) {
		/// <summary>
		/// Sets a record state.  Used for publish/decline, etc.
		/// </summary>
		/// <param name="recordID">The ID of the record to return.</param>      
		/// <param name="recordState">The State of the record (from enum)</param>      
		/// <param name="reason">The reason that the record is in this state.  May be null if not an Error state.</param>      			
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      
		/// <param name="comment">The comment to send to the user with any notification, if any.</param>      			

		if ($.isFunction(comment)) {
			callback = comment;
			comment = "";
		}

		if (!checkApiIntialized(callback)) return;

		//note - this method has been changed to a POST so that the reason can be as long as needed.
		if (reason == undefined || reason == null) reason = "";
		if (comment == undefined || comment == null) comment = "";

		var postData = { reason: reason, comment: comment };


		var url = _buildAPIUrl("SetRecordStateEx", [recordID, recordState]);

		_submitPostData(url, postData, callback);

	}

	API.SetRecordFileState = function (recordID, fieldName, fileServiceState, callback) {
		/// <summary>
		/// Sets the file service state on a given file service field.
		/// </summary>
		/// <param name="recordID">The ID of the record to return.</param>      
		/// <param name="fieldName">The name of the field the file is associated with on the record.</param>      
		/// <param name="fileServiceState">The fileServiceState to set the field to.  Normally this will be FileServiceState.Unprocessed.</param>      			
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if (!checkApiIntialized(callback)) return;

		var url = _buildAPIUrl("SetRecordFileState", [recordID, fieldName, fileServiceState]);

		jQuery.getJSON(url, callback);
	}

	API.UpdateRecordTypeAlert = function (recordTypeID, recordState, moderatorID, otherNotificationType, callback) {
		/// <summary>
		/// Inserts or updates a RecordTypeAlert.
		/// </summary>
		/// <param name="recordTypeID"></param>      
		/// <param name="recordState"></param>      
		/// <param name="moderatorID"></param>      	
		/// <param name="otherNotificationType"></param>		
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if (!checkApiIntialized(callback)) return;

		if (callback == undefined && jQuery.isFunction(otherNotificationType)) {
			callback = otherNotificationType;
			otherNotificationType = null;
		}

		var query = null;
		if (otherNotificationType != null && otherNotificationType != "") {
			query = "otherNotificationType=" + escape(otherNotificationType);
		}

		var url = _buildAPIUrl("UpdateRecordTypeAlert", [recordTypeID, recordState, moderatorID], query);

		jQuery.getJSON(url, callback);
	}

	API.DeleteRecordTypeAlert = function (recordTypeID, recordState, moderatorID, otherNotificationType, callback) {
		/// <summary>
		/// Deletes a RecordTypeAlert.
		/// </summary>
		/// <param name="recordTypeID"></param>      
		/// <param name="recordState"></param>      
		/// <param name="moderatorID"></param>      			
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if (!checkApiIntialized(callback)) return;

		if (callback == undefined && jQuery.isFunction(otherNotificationType)) {
			callback = otherNotificationType;
			otherNotificationType = null;
		}

		var query = null;
		if (otherNotificationType != null && otherNotificationType != "") {
			query = "otherNotificationType=" + escape(otherNotificationType);
		}

		var url = _buildAPIUrl("DeleteRecordTypeAlert", [recordTypeID, recordState, moderatorID], query);

		jQuery.getJSON(url, callback);
	}

	API.GetRecordTypeAlerts = function (recordTypeID, callback) {
		/// <summary>
		/// Gets the RecordTypeAlerts from a specific recordTypeID
		/// </summary>
		/// <param name="recordTypeID"></param>         			
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if (!checkApiIntialized(callback)) return;

		var url = _buildAPIUrl("GetRecordTypeAlerts", [recordTypeID]);

		jQuery.getJSON(url, callback);
	}


	API.ClearCache = function (cacheKey, callback) {
		/// <summary>
		/// Clears the cache for a given cache key that was previously passed into the SearchRecords call in the SearchArg parameter.
		/// </summary>
		/// <param name="cacheKey">A cache key that was previously passed into the SearchRecords call in the SearchArg parameter.</param>         			
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if (!checkApiIntialized(callback)) return;

		var url = _buildAPIUrl("ClearCache", [cacheKey]);

		jQuery.getJSON(url, callback);
	}


	API.SaveRecord = function (record, callback, cacheKey) {
		/// <summary>
		/// Saves a record.
		/// </summary>
		/// <param name="record">The record to save.</param>      
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      
		/// <param name="cacheKey">Optional - the cacheKey to clear the cache for.  Use this in conjunction with the same cacheKey on calls to SearchRecords to control the cache.</param>      

		if (!checkApiIntialized(callback)) return;

		if (record == null) callback(API.ResponseType.Error, "The record object cannot be null.");
		if (record.RecordTypeName == undefined || record.RecordTypeName == null || record.RecordTypeName == "") {
			callback({
				ResponseType: API.ResponseType.Error,
				Message: "The RecordTypeName property cannot be null."
			});
			return;
		}


		//get the record type
		API.GetRecordTypeByName(record.RecordTypeName, function (response) {

			if (response.ResponseType == API.ResponseType.Error) {
				callback(response);
				return;
			} else {

				//check the record type
				var recordType = response.ResponseData;
				if (recordType == undefined || recordType == null) {
					callback({
						ResponseType: API.ResponseType.Error,
						Message: "The Record Type " + record.RecordTypeName + " could not be found."
					});
					return;
				}


				//validate the field types...
				var lstFieldErrors = [];
				for (var i = 0; i < recordType.FieldTypes.length; i++) {
					var fieldType = recordType.FieldTypes[i];
					var fieldValue = record[fieldType.Name];

					if (fieldValue != undefined && fieldValue != null) {
						//check for a file value..
						if (fieldValue.OriginalFilePath != undefined) fieldValue = fieldValue.OriginalFilePath;
					}


					var fieldLabel = recordType.FieldTypes[i].Label;



					if (fieldType.AllowNull == false) {
						//req field
						if (fieldValue == undefined || fieldValue == null || new String(fieldValue).length == 0) {

							lstFieldErrors[lstFieldErrors.length] = {
								FieldType: fieldType,
								Message: "The " + fieldType.Label + " field is required."
							};
						}
					}

					if (fieldValue == undefined || fieldValue == null || fieldValue == "") {
						//skip null vals if they are allowed...
						continue;
					}


					switch (fieldType.DataType) {

						case API.DataType.Boolean:
							if (new String(fieldValue).toLowerCase() != "true"
								&& new String(fieldValue).toLowerCase() != "false") {
								lstFieldErrors[lstFieldErrors.length] = {
									FieldType: fieldType,
									Message: "Could not convert value " + fieldValue + " from field " + fieldType.Label + " to a boolean."
								};
							}
							break;
						case API.DataType.DateTime:

							var dt = new Date(fieldValue);
							if (dt == null) {
								lstFieldErrors[lstFieldErrors.length] = {
									FieldType: fieldType,
									Message: "Could not convert value " + fieldValue + " from field " + fieldType.Label + " to a date/time."
								};
							}
							break;
						case API.DataType.Double:

							if (isNaN(parseFloat(fieldValue))) {
								lstFieldErrors[lstFieldErrors.length] = {
									FieldType: fieldType,
									Message: "Could not convert value " + fieldValue + " from field " + fieldType.Label + " to a number."
								};
							}
							break;
						case API.DataType.Int:
							if (isNaN(parseInt(fieldValue))) {
								lstFieldErrors[lstFieldErrors.length] = {
									FieldType: fieldType,
									Message: "Could not convert value " + fieldValue + " from field " + fieldType.Label + " to a number."
								};
							}
							break;
						case API.DataType.String:
							//enforce max length...				
							if (fieldType.MaxLength == 0 && fieldValue.Length > 400) {
								lstFieldErrors[lstFieldErrors.length] = {
									FieldType: fieldType,
									Message: "The value from field " + fieldType.Label + " must be 400 characters or less."
								};

							}
							else if (fieldType.MaxLength > 0 && fieldValue.Length > fieldType.MaxLength) {
								lstFieldErrors[lstFieldErrors.length] = {
									FieldType: fieldType,
									Message: "The value from field " + fieldType.Label + " must be " + fieldType.MaxLength + " characters or less."
								};
							}

							var o1 = _enforceRegex(fieldType, fieldValue, fieldLabel);
							if (o1 != null) {
								lstFieldErrors[lstFieldErrors.length] = o1;
							}

							break;

						default:
							//textblob - enforce max length

							if (fieldType.MaxLength > 0 && fieldValue.Length > fieldType.MaxLength) {
								lstFieldErrors[lstFieldErrors.length] = {
									FieldType: fieldType,
									Message: "The value from field " + fieldType.Label + " must be " + fieldType.MaxLength + " characters or less."
								};
							}

							var o2 = _enforceRegex(fieldType, fieldValue, fieldLabel);
							if (o2 != null) {
								lstFieldErrors[lstFieldErrors.length] = o2;
							}
							break;
					}

				}

				//check for validation errors
				if (lstFieldErrors.length > 0) {
					var msg = "";
					for (var x = 0; x < lstFieldErrors.length; x++) {
						msg += " - " + lstFieldErrors[x].Message + "\n";
					}

					callback({
						ResponseType: API.ResponseType.Error,
						Message: msg,
						ValidationErrors: lstFieldErrors

					});
					return;
				}



				//do the deed
				var postData = Agility.JSON.encode(record);
				if (cacheKey == undefined || cacheKey == null) cacheKey = "";

				var url = _buildAPIUrl("SaveRecord", [], "cacheKey=" + escape(cacheKey));

				_submitPostData(url, postData, callback);
			}
		});

	}


	function _enforceRegex(fieldType, fieldValue, fieldLabel) {
		if (fieldValue == undefined || fieldValue == null || fieldValue == "" || typeof fieldValue != "string") return null;

		//enfore regex
		if (fieldType.ValidationRegEx != null && fieldType.ValidationRegEx != "" && fieldType.ValidationRegEx != "Add comma separated file extensions. Eg: .pdf, .gif") {
			try {
				var rEx = new RegExp(fieldType.ValidationRegEx);
				rEx.ignoreCase = true;

				if (fieldValue.search(rEx) == -1) {


					if (fieldType.ValidationMessage != null && fieldType.ValidationMessage != "" && fieldType.ValidationMessage != "Add the file type validation message.") {
						return {
							FieldType: fieldType,
							Message: fieldType.ValidationMessage
						};
					}
					else {
						return {
							FieldType: fieldType,
							Message: "The value from field " + fieldType.Label + " did not match the validation expression requirements."
						};
					}
				}
			} catch (Error) {
				//ignore regex errors...
			}
		}
	}

	API.SearchRecords = function (searchArg, callback) {
		/// <summary>
		/// Searches for records based on a SearchArg object.
		/// </summary>
		/// <param name="searchArg" type="Agility.UGC.API.SearchArg">The search arg object.</param>      
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if (!checkApiIntialized(callback)) return;

		var argStr = Agility.JSON.encode(searchArg);
		var url = _buildAPIUrl("SearchRecords");
		url += "&s=" + escape(argStr);

		jQuery.getJSON(url, callback);

	}

	API.SearchComments = function (commentSearchArg, callback) {
		/// <summary>
		/// Searches for records based on a SearchArg object.
		/// </summary>
		/// <param name="commentSearchArg" type="Agility.UGC.API.CommentSearchArg">The search arg object.</param>      
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if (!checkApiIntialized(callback)) return;

		var argStr = Agility.JSON.encode(commentSearchArg);
		var url = _buildAPIUrl("SearchComments");
		url += "&s=" + escape(argStr);

		jQuery.getJSON(url, callback);

	}

	API.SearchCommentParentIDs = function (commentSearchArg, callback) {
		/// <summary>
		/// Searches for parent record ids for comments based on a SearchArg object.
		/// </summary>
		/// <param name="commentSearchArg" type="Agility.UGC.API.CommentSearchArg">The search arg object.</param>      
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if (!checkApiIntialized(callback)) return;

		var argStr = Agility.JSON.encode(commentSearchArg);
		var url = _buildAPIUrl("SearchCommentParentIDs");
		url += "&s=" + escape(argStr);

		jQuery.getJSON(url, callback);

	}

	API.GetFeedbackAggregate = function (searchArg, callback) {
		/// <summary>
		/// Searches for records based on a SearchArg object.
		/// </summary>
		/// <param name="searchArg" type="Agility.UGC.API.SearchArg">The search arg object.</param>      
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if (!checkApiIntialized(callback)) return;

		var argStr = Agility.JSON.encode(searchArg);
		var url = _buildAPIUrl("GetFeedbackAggregate");
		url += "&s=" + escape(argStr);

		jQuery.getJSON(url, callback);

	}

	API.SaveRating = function (ratingFeedback, callback) {
		var rating = ratingFeedback;
		rating.SubmissionType = API.FeedbackSubmissionType.Rating;
		API.SaveRatingFeedback(rating, callback);
	}

	API.SaveLike = function (booleanFeedback, callback) {
		var like = booleanFeedback;
		like.SubmissionType = API.FeedbackSubmissionType.Like;
		like.IsPositive = true;

		API.SaveBooleanFeedback(like, callback);
	}

	API.SaveVote = function (booleanFeedback, callback) {
		var vote = booleanFeedback;
		vote.IsPositive = true;
		vote.SubmissionType = API.FeedbackSubmissionType.Vote;

		API.SaveBooleanFeedback(vote, callback);
	}

	API.SaveApproveDisapprove = function (booleanFeedback, IsApproved, callback) {
		var ap = booleanFeedback;
		ap.IsPositive = IsApproved;
		ap.SubmissionType = API.FeedbackSubmissionType.ApproveDisapprove;

		API.SaveBooleanFeedback(ap, callback);
	}


	API.SaveRatingFeedback = function (ratingFeedback, callback) {
		/// <summary>
		/// Saves a Rating Feedback Object
		/// </summary>
		/// <param name="ratingFeedback">The rating feedback to save.</param>      
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if (!checkApiIntialized(callback)) return;

		if (API.APIProfileRecordID > 0) {
			ratingFeedback.ProfileRecordID = API.APIProfileRecordID;
		}

		var postData = Agility.JSON.encode(ratingFeedback);

		var url = _buildAPIUrl("SaveRatingFeedback", null);

		_submitPostData(url, postData, callback);

	}

	API.SaveBooleanFeedback = function (booleanFeedback, callback) {
		/// <summary>
		/// Saves a boolean feedback object
		/// </summary>
		/// <param name="booleanFeedback">The boolean feedback to save.</param>      
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if (!checkApiIntialized(callback)) return;

		if (API.APIProfileRecordID > 0) {
			booleanFeedback.ProfileRecordID = API.APIProfileRecordID;
		}



		var postData = Agility.JSON.encode(booleanFeedback);

		var url = _buildAPIUrl("SaveBooleanFeedback", null);

		_submitPostData(url, postData, callback);

	}

	var _settingsCache = null;

	API.GetSettings = function (callback, ignoreCache) {
		/// <summary>
		/// Get the Settings.
		/// </summary>
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      
		/// <param name="ignoreCache" type="Boolean">Whether to ignore the cached value and get the settings from the server every time.<param>

		if (!checkApiIntialized(callback)) return;

		if (_settingsCache == null || ignoreCache == false) {

			var url = _buildAPIUrl("GetSettings", null);

			jQuery.getJSON(url, function (data) {
				if (data.ResponseType != API.ResponseType.OK) {
					callback(data);
				} else {
					_settingsCache = data.ResponseData;
					callback(data);
				}
			});
		} else {

			//use the cached value...
			callback({
				ResponseType: API.ResponseType.OK,
				ResponseData: _settingsCache
			});
		}

	}

	API.SaveSettings = function (settings, callback) {
		/// <summary>
		/// Saves the Settings
		/// </summary>
		/// <param name="settings">The settings to save.</param>      
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if (!checkApiIntialized(callback)) return;

		_settingsCache = null;

		var postData = Agility.JSON.encode(settings);

		var url = _buildAPIUrl("SaveSettings", null);

		_submitPostData(url, postData, callback);

	}

	API.GetAllSyndicationServices = function (callback) {
		/// <summary>
		/// Gets all of the syndication services that have been configured.
		/// </summary>
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if (!checkApiIntialized(callback)) return;

		var url = _buildAPIUrl("GetAllSyndicationServices", null);

		jQuery.getJSON(url, callback);

	}

	API.DeleteSyndicationService = function (syndicationServiceID, callback) {
		/// <summary>
		/// Deletes a syndication service.
		/// </summary>
		/// <param name="syndicationServiceID" type="Number">The ID of the syndication service</param>
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if (!checkApiIntialized(callback)) return;

		var url = _buildAPIUrl("DeleteSyndicationService", [syndicationServiceID]);

		jQuery.getJSON(url, callback);

	}


	API.SaveSyndicationService = function (syndicationService, callback) {
		/// <summary>
		/// Saves the Settings
		/// </summary>
		/// <param name="syndicationService">The settings to save.</param>      
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if (!checkApiIntialized(callback)) return;

		var postData = Agility.JSON.encode(syndicationService);

		var url = _buildAPIUrl("SaveSyndicationService", null);

		_submitPostData(url, postData, callback);

	}


	API.GetAllSystemAccess = function (callback) {
		/// <summary>
		/// Gets all of the System Access names in the system/
		/// </summary>
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if (!checkApiIntialized(callback)) return;

		var url = _buildAPIUrl("GetAllSystemAccess", null);

		jQuery.getJSON(url, callback);

	}

	API.GetGUID = function (callback) {
		/// <summary>
		/// Get a GUID.
		/// </summary>
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>      

		if (!checkApiIntialized(callback)) return;

		var url = _buildAPIUrl("GetGUID", null);

		jQuery.getJSON(url, callback);
	}



	API.GetAmazonS3Signature = function (policy, callback) {
		/// <summary>
		/// Gets a signature for an Amazon S3 call.
		/// </summary>
		/// <param name="policy" type="String"></param>      
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>

		if (!checkApiIntialized(callback)) return;

		var url = _buildAPIUrl("GetAmazonS3Signature", null, "policy=" + escape(policy));

		jQuery.getJSON(url, callback);

	}

	API.GetOoyalaSignature = function (queryString, callback) {
		/// <summary>
		/// Gets a signature for an Ooyala upload.
		/// </summary>
		/// <param name="queryString" type="String"></param>     
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>

		if (!checkApiIntialized(callback)) return;

		var url = _buildAPIUrl("GetOoyalaSignature", null, "p=" + escape(queryString));

		jQuery.getJSON(url, callback);

	}

	API.DeleteFile = function (key, callback) {
		/// <summary>
		/// Deletes a file from the Amazon S3 bucket
		/// </summary>
		/// <param name="key" type="String">The full key that points to the file in S3.</param>      
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>

		if (!checkApiIntialized(callback)) return;

		var url = _buildAPIUrl("DeleteFile", null, "key=" + escape(key));

		jQuery.getJSON(url, callback);

	}

	var _fileSizeCache = {};

	API.GetFileSize = function (key, callback) {
		/// <summary>
		/// Get the file size from a file in the Amazon S3 bucket.
		/// </summary>
		/// <param name="key" type="String">The full key that points to the file in S3.</param>      
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>

		if (!checkApiIntialized(callback)) return;

		var url = _buildAPIUrl("GetFileSize", null, "key=" + escape(key));

		if (_fileSizeCache[key] == undefined) {
			jQuery.getJSON(url, function (data) {
				if (data.ResponseType == API.ResponseType.OK) {
					_fileSizeCache[key] = new Number(data.ResponseData);
				}
				callback(data);
			});
		} else {
			callback({
				ResponseType: API.ResponseType.OK,
				ResponseData: new Number(_fileSizeCache[key])
			});
		}

	}



	API.TestBrightcoveAuthenticationSettings = function (readToken, writeToken, callback) {

		var url = API.APIUrl.toLowerCase().replace("agility-ugc-api-jsonp.svc", "TestBrightcoveAuth.ashx");

		if ($.isFunction(writeToken)) {

			var videoID = readToken;
			callback = writeToken;

			url += "?accessKey=" + escape(API.APIAccessKey);
			url += "&seconds=" + escape(API.APISeconds);
			url += "&randomNumber=" + escape(API.APIRandomNumber);
			url += "&hash=" + escape(API.APIHash);
			url += "&profileRecordID=" + escape(API.APIProfileRecordID);
			url += "&videoID=" + videoID;
			url += "&method=?";

		} else {

			url = API.APIUrl.toLowerCase().replace("agility-ugc-api-jsonp.svc", "TestBrightcoveAuth.ashx");
			url += "?readToken=" + escape(readToken);
			url += "&writeToken=" + escape(writeToken);
			url += "&method=?";
		}
		jQuery.getJSON(url, function (retObj) {
			var isDisabled = false;
			if (retObj.videoIsDisabled != undefined && retObj.videoIsDisabled == true) isDisabled = true;
			callback(retObj.valid, retObj.videoExists, isDisabled);
		});
	};

	API.TestYouTubeAuthenticationSettings = function (applicationName, apiKey, username, password, callback) {

		var url = API.APIUrl.toLowerCase().replace("agility-ugc-api-jsonp.svc", "TestYouTubeAuth.ashx");
		url += "?applicationName=" + escape(applicationName);
		url += "&apiKey=" + escape(apiKey);
		url += "&username=" + escape(username);
		url += "&password=" + escape(password);
		url += "&method=?";

		jQuery.getJSON(url, callback);
	};

	API.TestAmazonS3AuthenticationSettings = function (bucket, apiKey, secretKey, callback) {
		var url = API.APIUrl.toLowerCase().replace("agility-ugc-api-jsonp.svc", "TestAmazonAuth.ashx");
		url += "?apiKey=" + escape(apiKey);
		url += "&bucket=" + escape(bucket);
		url += "&secretKey=" + escape(secretKey);
		url += "&method=?";

		jQuery.getJSON(url, callback);

	}

	API.GenerateTwitterOauthRequestToken = function (key, secret, callback) {

		var url = API.APIUrl.toLowerCase().replace("agility-ugc-api-jsonp.svc", "GenerateTwitterOauthRequestToken.ashx");
		url += "?accessKey=" + escape(API.APIAccessKey);
		url += "&seconds=" + escape(API.APISeconds);
		url += "&randomNumber=" + escape(API.APIRandomNumber);
		url += "&hash=" + escape(API.APIHash);
		url += "&profileRecordID=" + escape(API.APIProfileRecordID);
		url += "&appkey=" + escape(key);
		url += "&appsecret=" + escape(secret);
		url += "&method=?";


		jQuery.getJSON(url, callback);

	}

	API.GetAmazonS3Form = function (options) {

		/// <summary>
		/// Builds the SWF form uploader that will upload data to Amazon S3.  The HTML will be appended to the jQuery element that is passed into the options argument (options.fieldPanel)
		/// </summary>
		/// <param name="options">The options for this input. Has the following properties: fieldName, inputID, fieldType  fieldPanel, swfUploadUrl, beforeUpload, uploadComplete, uploadError, uploadProgress, uploadButtonImageUrl, uploadButtonImageHeight, uploadButtonImageWidth, </param>        

		if (options == undefined || options == null || typeof (options) == "string") {
			throw new Error("The Amazon S3 form parameters have not been set correctly.");
		}

		var returnUrl = location.href;
		var inputID = options.inputID;
		var fieldPanel = options.fieldPanel;
		var fieldName = options.fieldName;

		var fieldType = null;


		if (options.fieldType != undefined) fieldType = options.fieldType;

		//get the settings...    
		API.GetSettings(function (data) {

			var html = null;

			if (data.ResponseType != API.ResponseType.OK) {
				html = jQuery("<span>There was an error building the " + options.fieldName + " input: " + data.Message + "</span>");
				html.appendTo(fieldPanel);
				return;
			} else {

				var settings = data.ResponseData;

				//if Amazon S3 is NOT enabled, just upload to UGC
				if (settings.AmazonS3Bucket == null || settings.AmazonS3Bucket == "") {
					API.GetUGCUploadForm(options);
					return;
				}

				//get a new GUID
				API.GetGUID(function (dataGUID) {

					if (dataGUID.ResponseType != API.ResponseType.OK) {
						html = jQuery("<span>There was an error building the " + options.fieldName + " input: " + dataGUID.Message + "</span>");
						html.appendTo(fieldPanel);
						return;
					} else {

						var GUID = dataGUID.ResponseData;

						//get the current filename stored in the field
						var currentFileKey = $("#" + inputID).val();

						//build the unique ids
						var uniqueID = Agility.UniqueID("post_AmazonFileForm");
						var uniqueFormID = uniqueID + "_form";
						var uniqueFileUploadID = uniqueID + "_fileupload";

						var uniqueSwfUploadID = uniqueID + "_swfupload";
						if (options.button_placeholder_id != undefined) {
							uniqueSwfUploadID = options.button_placeholder_id;
						}
						var uniqueIFrameID = uniqueID + "_iframe";
						var uniqueUploadProgressID = uniqueID + "_progress";

						//check for flash version...						
						var flashVersion = Agility.GetFlashVersion();

						//build the return url to come back to the blank.htm page

						returnUrl = returnUrl.substring(0, returnUrl.indexOf("/", returnUrl.indexOf("//") + 2));
						returnUrl += Agility.ResolveUrl("~/AgilityRedirector.htm");

						var blankUrl = Agility.ResolveUrl("~/AgilityRedirector.htm");


						var amazonS3Url = settings.AmazonS3BaseUrl;
						if (amazonS3Url == undefined || amazonS3Url == null || amazonS3Url == "") {
							html = jQuery("The Amazon S3 Base Url settings value has not be provided.");
							html.appendTo(fieldPanel);
							return;
						}

						if (amazonS3Url.lastIndexOf("/") != amazonS3Url.length - 1) amazonS3Url += "/";

						//yyyy-MM-dd\\THH:mm:ss.fff\\Z 
						var exDate = new Date();
						exDate.setDate(exDate.getDate() + 1);
						var expiration = exDate.getFullYear() + "-" + (exDate.getMonth() + 1) + "-" + exDate.getDate() + "T" + exDate.getHours() + ":" + exDate.getMinutes() + ":" + exDate.getSeconds() + "." + exDate.getTimezoneOffset() + "Z";

						var baseDirectory = "AgilityUGC/" + GUID;

						var contentLengthRangeMin = 0;
						var contentLengthRangeMax = 0;
						if (fieldType != null && fieldType.MaxLength > 0) {
							//convert from kb to bytes...
							contentLengthRangeMax = fieldType.MaxLength * 1024;
						}

						var policy = '{ "expiration": "' + expiration + '", ';
						policy += '"conditions": [  ';
						policy += '	{"bucket": "' + settings.AmazonS3Bucket + '"}, ';
						policy += '	["starts-with", "$key", "' + baseDirectory + '"], ';
						policy += '	{"acl": "public-read"}, ';
						if (flashVersion <= 8) {
							policy += '	{"success_action_redirect": "' + returnUrl + '"}, ';
						} else {
							policy += '	{"success_action_status": "201"}, ';

						}
						policy += '	 ["starts-with", "$Content-Type", ""], [ "starts-with", "$filename", "" ], ';
						if (contentLengthRangeMax > 0) {
							policy += ' ["content-length-range", ' + contentLengthRangeMin + ', ' + contentLengthRangeMax + '], ';
						}

						policy += '] }';



						//build the key
						var awsSecretKey = settings.AmazonS3SecretKey;
						var awskey = settings.AmazonS3AccessKey;

						//get the signature from the UGC Server
						API.GetAmazonS3Signature(policy, function (data) {
							var policySig = data.ResponseData;


							var fileTypes = null;
							//assume that the file extensions are the validation.... eg: .*([\.jpg]|[\.gif]|[\.jpeg]|[\.png])						
							if (fieldType != null && fieldType.ValidationRegEx != undefined && fieldType.ValidationRegEx != "") {

								var validExp = fieldType.ValidationRegEx;
								validExp = validExp.replace(".*([\\", "");
								validExp = validExp.replace("])", "");

								while (validExp.indexOf("]|[\\") != -1) {
									validExp = validExp.replace("]|[\\", ";");
								}
								fileTypes = validExp.replace(/\./g, "*.");

							}

							//IF FLASH IS ENABLED AND swfUpload is defined...
							if (Agility.GetFlashVersion() > 8
								&& options.swfUploadUrl != undefined
								&& options.swfUploadUrl != null) {
								html = jQuery("<span id='" + uniqueSwfUploadID + "'></span>");

								html.appendTo(fieldPanel);

								var postParams = {
									"AWSAccessKeyId": awskey,
									"policy": policySig.PolicyBase64,
									"acl": "public-read",
									"Signature": policySig.Signature,
									"success_action_status": "201"
								};


								//build the upload swf
								var swfu = new SWFUpload({
									// Backend Settings
									upload_url: amazonS3Url,
									file_post_name: "file",
									post_params: postParams,

									// File Upload Settings
									file_size_limit: contentLengthRangeMax / 1024,
									file_types: fileTypes,
									file_types_description: "Valid File Types",
									file_upload_limit: "0",    // Zero means unlimited


									// Event Handler Settings - these functions as defined in Handlers.js
									//  The handlers are not part of SWFUpload but are part of my website and control how
									//  my website reacts to the SWFUpload events.
									file_queue_error_handler: function (file, errorCode, message) {
										jQuery("#" + options.inputID).data("uploading", false);
										if (options.uploadError != undefined && jQuery.isFunction(options.uploadError)) {
											options.uploadError(file, errorCode, message);
										}
									},
									file_dialog_complete_handler: function (numFilesSelected, numFilesQueued) {
										if (numFilesQueued > 0) {
											var file = this.getFile();
											if (file == null) return;

											var filename = file.name;

											//get the mimetype
											var mimetype = API.GetMIMEtype(filename);
											this.addPostParam("Content-Type", API.GetMIMEtype(filename));

											//set the key based on the filename, minus any invalid characters...
											var filenameStripped = filename.replace(/[^a-zA-Z0-9-_\.]/g, "");
											if (filenameStripped == "" || filenameStripped.lastIndexOf(".") == 0) {

											}


											this.addPostParam("key", baseDirectory + "/" + filenameStripped);

											if (options.beforeUpload != undefined && jQuery.isFunction(options.beforeUpload)) {
												if (options.beforeUpload(filename, mimetype, file.size, this) == false) {
													return;
												}
											}

											jQuery("#" + options.inputID).data("uploading", true);
											this.startUpload();
										}

									},
									upload_start_handler: function (file) {
										//check that the file name has SOME valid characters in it...
										var filenameStripped = file.name.replace(/[^a-zA-Z0-9-_\.]/g, "");
										if (filenameStripped == "" || filenameStripped.lastIndexOf(".") == 0) {
											return false;
										}
									},
									upload_progress_handler: function (file, bytesLoaded) {
										if (options.uploadProgress != undefined && jQuery.isFunction(options.uploadProgress)) {
											options.uploadProgress(file, bytesLoaded);
										}
									},
									upload_success_handler: function (file, serverData) {

										if (jQuery.browser.msie) {
											var xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
											xmlDoc.loadXML(serverData);
											serverData = xmlDoc;
										}

										//success - set the value of the input and call the event.
										var uploadedKey = $(serverData).find("Key").text();
										if (uploadedKey == null || uploadedKey == "") {
											var filenameStripped = file.name.replace(/[^a-zA-Z0-9-_\.]/g, "");
											uploadedKey = baseDirectory + "/" + filenameStripped;
										}

										jQuery("#" + options.inputID).val(uploadedKey);
										jQuery("#" + options.inputID).data("uploading", false);
										if (options.uploadComplete != undefined && jQuery.isFunction(options.uploadComplete)) {
											options.uploadComplete(uploadedKey, file.size);
										}
									},
									upload_complete_handler: function (file) {
										jQuery("#" + options.inputID).data("uploading", false);
									},

									upload_error_handler: function uploadError(file, errorCode, message) {
										jQuery("#" + options.inputID).data("uploading", false);
										if (options.uploadError != undefined && jQuery.isFunction(options.uploadError)) {
											options.uploadError(file, errorCode, message);
										}
									},
									swfupload_loaded_handler: function () {

									},

									// Button settings
									button_image_url: options.uploadButtonImageUrl,
									button_placeholder_id: uniqueSwfUploadID,
									button_width: options.uploadButtonImageWidth,
									button_height: options.uploadButtonImageHeight,
									button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
									button_action: SWFUpload.BUTTON_ACTION.SELECT_FILE,
									button_cursor: SWFUpload.CURSOR.HAND,


									// Flash Settings
									prevent_swf_caching: false,
									flash_url: options.swfUploadUrl, // Relative to this file

									// Debug Settings
									debug: false
								});



							} else {

								//flash not enabled...

								//build the html for the form and iframe
								var formHtml = "<form  enctype=\"multipart/form-data\" id=\"" + uniqueFormID + "\" method=\"post\" action=\"" + amazonS3Url + "\" target=\"" + uniqueIFrameID + "\"  style=\"display:inline\">";
								formHtml += "<input type=\"hidden\" name=\"key\" />";
								formHtml += "<input type=\"hidden\" name=\"AWSAccessKeyId\" />";
								formHtml += "<input type=\"hidden\" name=\"Content-Type\" value=\"\">";
								formHtml += "<input type=\"hidden\" name=\"success_action_redirect\" value=\"" + returnUrl + "\" />";
								formHtml += "<input type=\"hidden\" name=\"acl\" value=\"public-read\" />";
								formHtml += "<input type=\"hidden\" name=\"policy\" value=\"\" />";
								formHtml += "<input type=\"hidden\" name=\"filename\" value=\"\" />";
								formHtml += "<input type=\"hidden\" name=\"Signature\" size=\"100\" value=\"\" />";
								formHtml += "<input id='" + uniqueFileUploadID + "' type=\"file\" name=\"file\" />";
								formHtml += "</form>"
								formHtml += "<iframe id=\"" + uniqueIFrameID + "\" name=\"" + uniqueIFrameID + "\" src=\"" + blankUrl + "\" style='position:absolute; height:1px; width: 1px; top:-1px; left: -10000px; overflow:hidden'></iframe>";

								//build the jQuery object for the form							
								html = jQuery(formHtml);

								//add the form to the field panel..							
								html.appendTo(fieldPanel);

								//set the field values...															
								jQuery("input[name='AWSAccessKeyId']", html).val(awskey);
								jQuery("input[name='policy']", html).val(policySig.PolicyBase64);
								jQuery("input[name='Signature']", html).val(policySig.Signature);



								var uploadedFileName = null;

								//listen to frame load events																			
								jQuery("#" + uniqueIFrameID).load(function () {
									//load event of the iframe...

									jQuery("#" + uniqueFileUploadID).show();

									var loc = null;
									try {
										loc = $(this.contentDocument.location)
									} catch (error) { }

									try {

										if (loc == undefined || loc == null) {
											loc = $(this.contentWindow.location)
										}

										var href = loc.attr("href");

										var key = Agility.QueryString("key", href);
										if (key != null && key != "") {
											var url = amazonS3Url + key;

											//update the input file...
											jQuery("#" + inputID).val(key);
											$("#" + inputID).data("uploading", false);

											//call the uploadComplete event, pass it the key
											if (options.uploadComplete != undefined && jQuery.isFunction(options.uploadComplete)) {
												options.uploadComplete(key, -1);
											}
										}
									} catch (Error) {
										$("#" + inputID).data("uploading", false);


										//call the uploadError event, pass it the Error object
										var errorMsg = "An error occurred while uploading."
										if (contentLengthRangeMax > 0) {
											errorMsg += " Please ensure the file is less than " + (contentLengthRangeMax / 1024) + "kb.";
										}

										if (options.uploadError != undefined && jQuery.isFunction(options.uploadError)) {
											options.uploadError("", -1, errorMsg);
											throw Error;
										} else {
											alert(errorMsg);
											throw Error;
										}
									}
								});

								//listen to file name changed events...							
								jQuery("input[name='file']", html).change(function () {
									uploadedFileName = $(this).val();

									if (uploadedFileName != "") {

										//check that filename is of the allowable types...
										if (fieldType != null) {
											try {
												if (!validateFileType(uploadedFileName, fieldType.ValidationRegEx)) {

													if (options.uploadError != undefined && jQuery.isFunction(options.uploadError)) {
														options.uploadError(uploadedFileName, SWFUpload.QUEUE_ERROR.INVALID_FILETYPE, fieldType.ValidationMessage);
													}

													$(this).val("");
													return;
												}

											} catch (ex3) {
												return;
											}
										}

										//ensure there are some valid characters in there...
										var unstrippedName = uploadedFileName;
										var uploadedFileName = uploadedFileName.replace(/[^a-zA-Z0-9-_\.]/g, "");
										if (uploadedFileName == "" || uploadedFileName.lastIndexOf(".") == 0) {
											if (options.uploadError != undefined && jQuery.isFunction(options.uploadError)) {
												options.uploadError(unstrippedName, SWFUpload.QUEUE_ERROR.INVALID_FILETYPE, fieldType.ValidationMessage);
											}
											$(this).val("");
											return;
										}

										//set the "key" field value based on the stripped file name
										jQuery("input[name='key']", html).val(baseDirectory + "/" + uploadedFileName);

										//get the mimeType of the file
										var mimeType = API.GetMIMEtype(uploadedFileName);

										var filesize = -1;

										try {
											if (this.files && this.files.length > 0) {
												filesize = this.files[0].size;
											}
										} catch (error) { }

										//fire the beforeUpload event
										if (options.beforeUpload != undefined && jQuery.isFunction(options.beforeUpload)) {
											if (options.beforeUpload(uploadedFileName, mimeType, filesize, null) == false) {
												try {
													$(this).val("");
												} catch (ex2) { }
												return;
											}
										}


										//put a marker on the file to show that it has changed...
										$("#" + inputID).data("uploading", true);
										$("#" + uniqueFormID + " input[name='Content-Type']").val(mimeType);

										//submit the form
										var f = jQuery("#" + uniqueFormID);
										$(this).hide();
										f.submit();

										//clear out the input and hide it
										try {
											$(this).val("");
										} catch (ex1) { }

									}

								});

							}

						});

					}

				});

			}

		});





		//bind a "live" event to the remove link (this will work for ANY remove link...);		
		if (API._liveAttachmentRemoveHandlerBound != true) {

			API._liveAttachmentRemoveHandlerBound = true;

			jQuery("form a.UploadedFileRemove").live("click", function () {
				if (confirm("Do you wish to delete this file?\n\nYou will not be able to recover this file, and any links to it will be broken")) {

					//get the associated field ID from the <form> tag
					var associatedFieldID = jQuery(this).parents("form").data("associatedFieldID");

					var elem = jQuery(this).parent("span.UploadedFileContainer")
					elem.html("Deleting file...");

					var key = jQuery("#" + associatedFieldID).val();
					API.DeleteFile(key, function (data) {
						if (data.ResponseType != 0) {
							elem.html("An error occurred: " + data.Message);
						} else {
							elem.html("");
							jQuery("#" + associatedFieldID).val("");
						}
					});
				}
			});
		}
	}

	function validateFileType(filepath, validExp) {

		if (validExp == null || validExp == "") return true;

		validExp = validExp.replace(".*([\\", "");
		validExp = validExp.replace("])", "");

		while (validExp.indexOf("]|[\\") != -1) {
			validExp = validExp.replace("]|[\\", ";");
		}
		fileTypes = validExp.replace(/\./g, "*.");

		var ary = fileTypes.split(";");

		var selectedExt = filepath.substring(filepath.lastIndexOf(".")).toLowerCase();
		if (selectedExt.indexOf(".") != -1) {
			selectedExt = selectedExt.substring(selectedExt.lastIndexOf(".")).toLowerCase();
		}

		for (var i in ary) {
			var ext = $.trim(ary[i]);
			if (ext == "" || ext.length < 2 || ext.indexOf("*") != 0) continue;
			ext = ext.substring(1).toLowerCase();
			if (ext == selectedExt) {
				return true;
			}
		}

		return false;

	}

	API.GetUGCUploadForm = function (options) {

		/// <summary>
		/// Builds the SWF form uploader that will upload data to UGC.  The HTML will be appended to the jQuery element that is passed into the options argument (options.fieldPanel)
		/// </summary>
		/// <param name="options">The options for this input. Has the following properties: fieldName, inputID, fieldType  fieldPanel, swfUploadUrl, beforeUpload, uploadComplete, uploadError, uploadProgress, uploadButtonImageUrl, uploadButtonImageHeight, uploadButtonImageWidth, </param>        

		if (options == undefined || options == null || typeof (options) == "string") {
			throw new Error("The upload form parameters have not been set correctly.");
		}

		var returnUrl = location.href;
		var inputID = options.inputID;
		var fieldPanel = options.fieldPanel;
		var fieldName = options.fieldName;

		var fieldType = null;


		if (options.fieldType != undefined) fieldType = options.fieldType;

		//get the current filename stored in the field
		var currentFileKey = $("#" + inputID).val();

		//build the unique ids
		var uniqueID = Agility.UniqueID("post_UGCFileForm");
		var uniqueFormID = uniqueID + "_form";
		var uniqueFileUploadID = uniqueID + "_fileupload";


		var uniqueSwfUploadID = uniqueID + "_swfupload";
		if (options.button_placeholder_id != undefined) {
			uniqueSwfUploadID = options.button_placeholder_id;
		}

		var uniqueIFrameID = uniqueID + "_iframe";
		var uniqueUploadProgressID = uniqueID + "_progress";

		//check for flash version...						
		var flashVersion = Agility.GetFlashVersion();

		//build the return url to come back to the blank.htm page

		returnUrl = returnUrl.substring(0, returnUrl.indexOf("/", returnUrl.indexOf("//") + 2));
		returnUrl += Agility.ResolveUrl("~/AgilityRedirector.htm");

		var blankUrl = Agility.ResolveUrl("~/AgilityRedirector.htm");


		var uploadUrl = API.APIUrl;
		uploadUrl = uploadUrl.substring(0, uploadUrl.toLowerCase().lastIndexOf("/")) + "/UploadFile.ashx";

		//yyyy-MM-dd\\THH:mm:ss.fff\\Z 
		var exDate = new Date();
		exDate.setDate(exDate.getDate() + 1);
		var expiration = exDate.getFullYear() + "-" + (exDate.getMonth() + 1) + "-" + exDate.getDate() + "T" + exDate.getHours() + ":" + exDate.getMinutes() + ":" + exDate.getSeconds() + "." + exDate.getTimezoneOffset() + "Z";

		var contentLengthRangeMin = 0;
		var contentLengthRangeMax = 0;
		if (fieldType != null && fieldType.MaxLength > 0) {
			//convert from kb to bytes...
			contentLengthRangeMax = fieldType.MaxLength * 1024;
		}

		var fileTypes = null;
		//assume that the file extensions are the validation.... eg: .*([\.jpg]|[\.gif]|[\.jpeg]|[\.png])						
		if (fieldType != null && fieldType.ValidationRegEx != undefined && fieldType.ValidationRegEx != "") {

			var validExp = fieldType.ValidationRegEx;
			validExp = validExp.replace(".*([\\", "");
			validExp = validExp.replace("])", "");

			while (validExp.indexOf("]|[\\") != -1) {
				validExp = validExp.replace("]|[\\", ";");
			}
			fileTypes = validExp.replace(/\./g, "*.");

		}

		//IF FLASH IS ENABLED....

		if (Agility.GetFlashVersion() > 8
			&& options.swfUploadUrl != undefined
			&& options.swfUploadUrl != null) {

			if (options.button_placeholder_id == undefined) {
				html = jQuery("<span id='" + uniqueSwfUploadID + "'></span>");
				html.appendTo(fieldPanel);
			}

			var button_window_mode = SWFUpload.WINDOW_MODE.TRANSPARENT;
			if (options.button_window_mode != undefined) button_window_mode = options.button_window_mode;


			var postParams = {
				"accessKey": API.APIAccessKey,
				"seconds": API.APISeconds,
				"randomNumber": API.APIRandomNumber,
				"hash": API.APIHash,
				"profileRecordID": API.APIProfileRecordID
			};


			//build the upload swf
			var swfu = new SWFUpload({
				// Backend Settings
				upload_url: uploadUrl,
				file_post_name: "file",
				post_params: postParams,

				// File Upload Settings
				file_size_limit: contentLengthRangeMax / 1024,
				file_types: fileTypes,
				file_types_description: "Valid File Types",
				file_upload_limit: "0",    // Zero means unlimited


				// Event Handler Settings - these functions as defined in Handlers.js
				//  The handlers are not part of SWFUpload but are part of my website and control how
				//  my website reacts to the SWFUpload events.
				file_queue_error_handler: function (file, errorCode, message) {
					jQuery("#" + options.inputID).data("uploading", false);
					if (options.uploadError != undefined && jQuery.isFunction(options.uploadError)) {
						options.uploadError(file, errorCode, message);
					}
				},
				file_dialog_complete_handler: function (numFilesSelected, numFilesQueued) {
					if (numFilesQueued > 0) {
						var file = this.getFile();
						if (file == null) return;

						var filename = file.name;

						//get the mimetype
						var mimetype = API.GetMIMEtype(filename);
						this.addPostParam("Content-Type", API.GetMIMEtype(filename));

						//set the key based on the filename, minus any invalid characters...
						var filenameStripped = filename.replace(/[^a-zA-Z0-9-_\.]/g, "");
						if (filenameStripped == "" || filenameStripped.lastIndexOf(".") == 0) {

						}


						if (options.beforeUpload != undefined && jQuery.isFunction(options.beforeUpload)) {
							if (options.beforeUpload(filename, mimetype, file.size, this) == false) {
								return;
							}
						}

						jQuery("#" + options.inputID).data("uploading", true);
						this.startUpload();
					}

				},
				upload_start_handler: function (file) {
					//check that the file name has SOME valid characters in it...
					var filenameStripped = file.name.replace(/[^a-zA-Z0-9-_\.]/g, "");
					if (filenameStripped == "" || filenameStripped.lastIndexOf(".") == 0) {
						return false;
					}
				},
				upload_progress_handler: function (file, bytesLoaded) {
					if (options.uploadProgress != undefined && jQuery.isFunction(options.uploadProgress)) {
						options.uploadProgress(file, bytesLoaded);
					}
				},
				upload_success_handler: function (file, serverData) {
					jQuery("#" + options.inputID).data("uploading", false);

					//try {
					var responseObj = Agility.JSON.decode(serverData);
					if (responseObj.ResponseType != API.ResponseType.OK) {
						//an error occurred...
						if (options.uploadError != undefined && jQuery.isFunction(options.uploadError)) {
							options.uploadError("", -1, responseObj.Message);
						}
					} else {
						//successful upload...
						var uploadedFileName = responseObj.ResponseData;

						//update the input file...
						jQuery("#" + inputID).val(uploadedFileName);


						//call the uploadComplete event, pass it the key
						if (options.uploadComplete != undefined && jQuery.isFunction(options.uploadComplete)) {
							options.uploadComplete(uploadedFileName, -1);
						}
					}

					/*
					} catch (error) {
						if (options.uploadError != undefined && jQuery.isFunction(options.uploadError)) {
							options.uploadError(file, 101, "An error occurred interpretting the results from the server.");
						}
					}	
					*/
				},
				upload_complete_handler: function (file) {
					jQuery("#" + options.inputID).data("uploading", false);
				},

				upload_error_handler: function uploadError(file, errorCode, message) {
					jQuery("#" + options.inputID).data("uploading", false);
					if (options.uploadError != undefined && jQuery.isFunction(options.uploadError)) {
						options.uploadError(file, errorCode, message);
					}
				},
				swfupload_loaded_handler: function () {

				},

				// Button settings
				button_image_url: options.uploadButtonImageUrl,
				button_placeholder_id: uniqueSwfUploadID,
				button_width: options.uploadButtonImageWidth,
				button_height: options.uploadButtonImageHeight,
				button_window_mode: button_window_mode,
				button_action: SWFUpload.BUTTON_ACTION.SELECT_FILE,
				button_cursor: SWFUpload.CURSOR.HAND,


				// Flash Settings
				prevent_swf_caching: false,
				flash_url: options.swfUploadUrl, // Relative to this file

				// Debug Settings
				debug: false
			});



		}
		else {

			//flash not enabled...

			//build the html for the form and iframe
			var formHtml = "<form  enctype=\"multipart/form-data\" id=\"" + uniqueFormID + "\" method=\"post\" action=\"" + uploadUrl + "\" target=\"" + uniqueIFrameID + "\"  style=\"display:inline\">";

			formHtml += "<input type=\"hidden\" name=\"accessKey\" />";
			formHtml += "<input type=\"hidden\" name=\"seconds\" />";
			formHtml += "<input type=\"hidden\" name=\"randomNumber\" />";
			formHtml += "<input type=\"hidden\" name=\"hash\" />";
			formHtml += "<input type=\"hidden\" name=\"profileRecordID\" />";
			formHtml += "<input type=\"hidden\" name=\"returnUrl\" />";
			formHtml += "<input id='" + uniqueFileUploadID + "' type=\"file\" name=\"file\" />";
			formHtml += "</form>"
			formHtml += "<iframe id=\"" + uniqueIFrameID + "\" name=\"" + uniqueIFrameID + "\" src=\"" + blankUrl + "\" style='position:absolute; height:1px; width: 1px; top:-1px; left: -10000px; overflow:hidden'></iframe>";

			//build the jQuery object for the form							
			html = jQuery(formHtml);

			//add the form to the field panel..							
			html.appendTo(fieldPanel);

			//set the field values...															
			jQuery("input[name='accessKey']", html).val(API.APIAccessKey);
			jQuery("input[name='seconds']", html).val(API.APISeconds);
			jQuery("input[name='randomNumber']", html).val(API.APIRandomNumber);
			jQuery("input[name='hash']", html).val(API.APIHash);
			jQuery("input[name='profileRecordID']", html).val(API.APIProfileRecordID);
			jQuery("input[name='returnUrl']", html).val(returnUrl);

			var uploadedFileName = null;

			//listen to frame load events																			
			jQuery("#" + uniqueIFrameID).load(function () {
				//load event of the iframe...					
				jQuery("#" + uniqueFileUploadID).show();

				var loc = null;
				try {
					loc = $(this.contentDocument.location)
				} catch (error) { }

				try {

					if (loc == undefined || loc == null) {
						loc = $(this.contentWindow.location)
					}

					var href = loc.attr("href");

					var responseType = Agility.QueryString("ResponseType", href);
					if (responseType == null) return;

					if (responseType != API.ResponseType.OK) {
						//an error occurred...
						var errorMsg = Agility.QueryString("Message", href);

						if (options.uploadError != undefined && jQuery.isFunction(options.uploadError)) {
							options.uploadError("", -1, errorMsg);
						}
					} else {
						//successful upload...
						var uploadedFileName = Agility.QueryString("ResponseData", href);

						//update the input file...
						jQuery("#" + inputID).val(uploadedFileName);
						$("#" + inputID).data("uploading", false);

						//call the uploadComplete event, pass it the key
						if (options.uploadComplete != undefined && jQuery.isFunction(options.uploadComplete)) {
							options.uploadComplete(uploadedFileName, -1);
						}
					}


				} catch (Error) {
					$("#" + inputID).data("uploading", false);


					//call the uploadError event, pass it the Error object
					var errorMsg = "An error occurred while uploading."
					if (contentLengthRangeMax > 0) {
						errorMsg += " Please ensure the file is less than " + (contentLengthRangeMax / 1024) + "kb.";
					}

					if (options.uploadError != undefined && jQuery.isFunction(options.uploadError)) {
						options.uploadError("", -1, errorMsg);
						throw Error;
					} else {
						alert(errorMsg);
						throw Error;
					}
				}
			});

			//listen to file name changed events...							
			jQuery("input[name='file']", html).change(function () {
				var fileElem = $(this);
				var fileDom = this;
				uploadedFileName = fileElem.val();

				if (uploadedFileName != "") {


					//check that filename is of the allowable types...
					if (fieldType != null) {
						try {
							if (!validateFileType(uploadedFileName, fieldType.ValidationRegEx)) {

								if (options.uploadError != undefined && jQuery.isFunction(options.uploadError)) {
									options.uploadError(uploadedFileName, SWFUpload.QUEUE_ERROR.INVALID_FILETYPE, fieldType.ValidationMessage);
								}

								fileElem.val("");
								return;
							}

						} catch (ex3) {
							return;
						}
					}

					//ensure there are some valid characters in there...
					var unstrippedName = uploadedFileName;
					var uploadedFileName = uploadedFileName.replace(/[^a-zA-Z0-9-_\.]/g, "");
					if (uploadedFileName == "" || uploadedFileName.lastIndexOf(".") == 0) {
						if (options.uploadError != undefined && jQuery.isFunction(options.uploadError)) {
							options.uploadError(unstrippedName, SWFUpload.QUEUE_ERROR.INVALID_FILETYPE, fieldType.ValidationMessage);
						}
						fileElem.val("");
						return;
					}


					//get the mimeType of the file
					var mimeType = API.GetMIMEtype(uploadedFileName);

					var uploaderObj = null;
					var filesize = -1;

					try {
						if (fileDom.files && fileDom.files.length > 0) {
							filesize = fileDom.files[0].size;
						}
					} catch (error) { }

					//fire the beforeUpload event
					if (options.beforeUpload != undefined && jQuery.isFunction(options.beforeUpload)) {
						if (options.beforeUpload(uploadedFileName, mimeType, filesize, uploaderObj) == false) {
							try {
								fileElem.val("");
							} catch (ex2) { }
							return;
						}
					}

					//put a marker on the file to show that it has changed...
					$("#" + inputID).data("uploading", true);
					$("#" + uniqueFormID + " input[name='Content-Type']").val(mimeType);

					//submit the form
					var f = jQuery("#" + uniqueFormID);
					fileElem.hide();
					f.submit();


					//clear out the input and hide it
					try {
						fileElem.val("");
					} catch (ex1) { }

				}

			});

		}



		//bind a "live" event to the remove link (this will work for ANY remove link...);		
		if (API._liveAttachmentRemoveHandlerBound != true) {

			API._liveAttachmentRemoveHandlerBound = true;

			jQuery("form a.UploadedFileRemove").live("click", function () {
				if (confirm("Do you wish to delete this file?\n\nYou will not be able to recover this file, and any links to it will be broken")) {

					//get the associated field ID from the <form> tag
					var associatedFieldID = jQuery(this).parents("form").data("associatedFieldID");

					var elem = jQuery(this).parent("span.UploadedFileContainer")
					elem.html("Deleting file...");

					var key = jQuery("#" + associatedFieldID).val();
					API.DeleteFile(key, function (data) {
						if (data.ResponseType != 0) {
							elem.html("An error occurred: " + data.Message);
						} else {
							elem.html("");
							jQuery("#" + associatedFieldID).val("");
						}
					});
				}
			});
		}
	}


	//global variable used to track whether the  live attachment remove randler event has been bound
	API._liveAttachmentRemoveHandlerBound = false;



	function _buildAPIUrl(methodName, args, query) {
		var url = API.APIUrl;
		if (url == "" || url == null) return null;
		//query should be in the form "q1=q1val&s2=s2val

		//ensure the url ends with /
		if (url.lastIndexOf("/") != url.length - 1) url += "/";

		//create the base url for the call
		url += methodName + "/" + API.APIAccessKey + "/" + API.APISeconds + "/" + API.APIRandomNumber + "/" + API.APIHash + "/" + API.APIProfileRecordID;

		//add the arguments for call
		if (args != undefined && $.isArray(args) && args.length > 0) {
			jQuery.each(args, function (index, arg) {
				url += "/" + escape(arg);
			});
		}

		//add the "method" param
		url += "?method=?";

		//add the query strings
		if (query != undefined && query != "") {
			url += "&" + query;
		}

		return url;
	}



	function _submitPostData(url, postData, callback) {

		//build the unique ids
		var uniqueID = Agility.UniqueID("postDataForm");

		var uniqueFormID = uniqueID + "_form";
		var uniqueIFrameID = uniqueID + "_iframe";

		//build the return url to come back to the AgilityRedirector.htm page
		var returnUrl = location.href;
		returnUrl = returnUrl.substring(0, returnUrl.indexOf("/", returnUrl.indexOf("//") + 2));
		returnUrl += Agility.ResolveUrl("~/AgilityRedirector.htm");

		var blankUrl = Agility.ResolveUrl("~/AgilityRedirector.htm?empty=1");

		//remove the method callback from the url
		url = url.replace("?method=?", "?method=post");

		//build the html for the form and iframe
		var formHtml = "<div id=\"" + uniqueID + "\" style='display:inline'><form id=\"" + uniqueFormID + "\" method=\"post\" action=\"" + url + "\" target=\"" + uniqueIFrameID + "\">";
		if (typeof postData == "string") {
			formHtml += "<input type=\"text\" name=\"postdata\" />";
		} else {
			for (prop in postData) {
				formHtml += "<input type=\"text\" name=\"" + prop + "\" />";
			}
		}
		formHtml += "<input type=\"text\" name=\"url\" />";
		formHtml += "</form>"
		formHtml += "<iframe id=\"" + uniqueIFrameID + "\" name=\"" + uniqueIFrameID + "\" src=\"" + blankUrl + "\"></iframe>";
		formHtml += "</div>";


		//add the elements to the DOM
		var thisBody = jQuery(document.body);
		jQuery(thisBody).append(formHtml);

		//create the actual DOM elements
		var div = jQuery("#" + uniqueID);
		var form = jQuery("#" + uniqueFormID);
		var iframe = jQuery("#" + uniqueIFrameID);

		if (typeof postData == "string") {
			$("input[name=postdata]", form).val(postData);
		} else {
			for (prop in postData) {
				$("input[name=" + prop + "]", form).val(postData[prop]);
			}
		}


		$("input[name=url]", form).val(returnUrl);

		//capture the load event of the form.
		jQuery(iframe).load(function () {

			var frm = window.frames[uniqueIFrameID];

			var responseType = responseType = API.ResponseType.Error;

			var loc = frm.location;

			if (loc != null) {
				var href = loc.href;

				if (Agility.QueryString("empty", href) == "1") {
					//wait until the empty iframe has loaded...
					//submit the form		
					setTimeout(function () {
						form.submit();
					}, 0);
					return;

				}

				responseType = parseInt(Agility.QueryString("ResponseType", href));
				if (isNaN(responseType)) responseType = API.ResponseType.Error;
			}


			var data = {
				ResponseType: responseType,
				Message: Agility.QueryString("Message", href),
				ResponseData: Agility.QueryString("ResponseData", href)
			};

			//call the callback
			setTimeout(function () {
				callback(data);
			}, 0);

			//remove the elements...						
			setTimeout(function () {
				div.empty();
			}, 0);

		});


	}


	var _authCookieName = null;

	API.GetAuthCookieName = function () {
		/// <summary>Gets the name of the cookie that will be used for authentication.</summary>
		if (_authCookieName == null) _authCookieName = "UGC_AUTH_" + API.APIAccessKey;
		return _authCookieName;
	}

	API.SetAuthCookieName = function (cookieName) {
		/// <summary>Gets the name of the cookie that will be used for authentication.</summary>
		return _authCookieName = cookieName;
	}


	API.Logout = function (websiteUserTypeName) {
		/// <summary>Logs a user out of the UGC system and removes their login cookie.</summary>
		/// <param name="websiteUserTypeName" type="String">The website user type reference name.</param>

		//set the cookie based on the API Access Key
		var cookieName = API.GetAuthCookieName();
		if (websiteUserTypeName != null) cookieName += websiteUserTypeName;
		var cookieDate = Date.today().addMonths(-1);
		var cookieValue = null;
		Agility.SetCookie(cookieName, cookieValue, cookieDate, "/", null, true);

	}

	API.Authenticate = function (websiteUserTypeName, login, password, persistCookie, loginFieldName, callback) {
		/// <summary>Authenticates a user based on their login and password and their website user type reference name.  Returns the authentication token on success, empty string on failure.  This will also set a cookie that will keep the user logged in for this session, or persist the cookie if desired.</summary>
		/// <param name="websiteUserTypeName" type="String">The website user type reference name.</param>
		/// <param name="login" type="String">The login, usually a username or email address.</param>
		/// <param name="password" type="String">The password.</param>
		/// <param name="persistCookie" type="Boolean">(Optional) Whether or not to persist the authentication token in a cookie.</param>		
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>

		if (callback == undefined) {
			if (loginFieldName != undefined && $.isFunction(loginFieldName)) {
				callback = loginFieldName;
				loginFieldName = "";
			}
		}

		if (callback == undefined) {
			if (persistCookie != undefined && $.isFunction(persistCookie)) {
				callback = persistCookie;
				persistCookie = false;
			}
		}

		if (loginFieldName == undefined) loginFieldName = "";

		if (!checkApiIntialized(callback)) return;
		var args = new Array();
		args.push(websiteUserTypeName);
		var argStr = Agility.JSON.encode(websiteUserTypeName);
		var url = _buildAPIUrl("Authenticate", args);
		url += "&l=" + escape(login) + "&p=" + escape(password) + "&fn=" + escape(loginFieldName);

		$.getJSON(url, function (data) {
			if (_processOdataError(data, callback)) return;
			if (data != undefined) {

				var token = data.ResponseData;

				if (token != undefined && token != null && token != "") {
					//set the cookie based on the API Access Key
					var cookieName = API.GetAuthCookieName();
					if (websiteUserTypeName != null) cookieName += websiteUserTypeName;

					var cookieValue = new String(token);
					var cookieDate = null;
					if (persistCookie) {
						cookieDate = Date.today().addMonths(1);
					}
					Agility.SetCookie(cookieName, cookieValue, cookieDate, "/", null, false);
				}
				//return the OK response and the authentication token
				callback({
					ResponseType: API.ResponseType.OK,
					ResponseData: token,
					Message: null
				});
			}
		});

	}

	API.IsAuthenticated = function (websiteUserTypeName, callback) {
		/// <summary>Determines if the current user is authenticated and returns the user ID if so.  If the profileRecordID returned is less than zero, the user is NOT authenticated.</summary>
		/// <param name="websiteUserTypeName" type="String">The website user type reference name to validate the current used based on.</param>
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>

		//grab the token the from the cookie
		var cookieName = API.GetAuthCookieName();
		if (websiteUserTypeName != null) cookieName += websiteUserTypeName;

		var token = Agility.GetCookie(cookieName);


		if (token == null || token == "") {
			callback({
				ResponseType: API.ResponseType.OK,
				ResponseData: false,
				Message: null
			});
			return;
		}

		if (!checkApiIntialized(callback)) return;
		var args = new Array();
		args.push(websiteUserTypeName);

		var url = _buildAPIUrl("IsAuthenticated", args);

		url += "&s=" + escape(token);

		$.getJSON(url, function (data) {
			if (data != undefined && data.ResponseData != undefined && data.ResponseData != null) {


				var profileRecordID = data.ResponseData.ProfileRecordID;
				var seconds = data.ResponseData.Seconds;
				var randomNumber = data.ResponseData.RandomNumber;
				var hash = data.ResponseData.AccessHash;
				var accessKey = data.ResponseData.AccessKey;

				//reset the API data for this session
				API.APIAccessKey = accessKey;
				API.APISeconds = seconds;
				API.APIRandomNumber = randomNumber;
				API.APIProfileRecordID = profileRecordID;
				API.APIHash = hash;

				//send results to the user...
				callback({
					ResponseType: API.ResponseType.OK,
					ResponseData: profileRecordID,
					Message: null
				});
			}
		});
	}

	API.ChangePassword = function (currentPassword, newPassword, callback) {
		/// <summary>Change the password for the current website user. The ResponseData parameter of the callback will be a true/false if the password change was successful.<summary>
		/// <param name="currentPassword" type="String">The user's current password.</param>
		/// <param name="newPassword" type="String">The user's new password.</param>
		/// <param name="newPassword" type="String">The user's new password.</param>
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>

		if (API.APIProfileRecordID < 1) {
			callback({
				ResponseType: API.ResponseType.Error,
				ResponseData: false,
				Message: "The user is not currently logged in."
			});
			return;
		}



		if (!checkApiIntialized(callback)) return;
		var args = new Array();

		var url = _buildAPIUrl("ChangePassword", args);
		url += "&c=" + escape(currentPassword);
		url += "&n=" + escape(newPassword);


		$.getJSON(url, function (data) {

			callback(data);

		});
	}

	API.RetrievePassword = function (websiteUserTypeName, login, callback) {
		/// <summary>Retrieve the password for the profile login name and type. The ResponseData parameter of the callback will be a true/false if the password change was successful.<summary>
		/// <param name="websiteUserTypeName" type="String">The website user type reference name.</param>
		/// <param name="login" type="String">The user's login (usually username or email address).</param>
		/// <param name="callback" type="Function">The method to callback to.  Has 1 parameter with the following object: {ResponseType, ResponseData, Message}.</param>


		if (!checkApiIntialized(callback)) return;
		var args = new Array();
		args.push(websiteUserTypeName);

		var url = _buildAPIUrl("RetrievePassword", args);
		url += "&l=" + escape(login);


		$.getJSON(url, function (data) {
			if (data != undefined && data.ResponseData != undefined && data.ResponseData != null) {

				//send results to the user...
				callback({
					ResponseType: API.ResponseType.OK,
					ResponseData: data.ResponseData,
					Message: null
				});
			}
		});
	}

	API.GetODataMetaData = function (callback) {
		/// <summary>Gets the OData meta data for this service.</summary>
		var url = _getOdataUrl();
		url += "/?$format=json&$callback=?";

		$.getJSON(url, function (data) {
			if (data != undefined && data.d != undefined && data.d != null) {
				if (_processOdataError(data, callback)) return;

				if (data.d != undefined && data.d != null) {
					callback({
						ResponseType: API.ResponseType.OK,
						Message: null,
						ResponseData: data.d
					});
				}
			}
		});

	}

	API.GetODataFeed = function (recordTypeName, options, callback) {

		var url = _getOdataUrl();
		url += "/" + recordTypeName + "/";

		url += "?$format=json"

		if (options != undefined && options != null) {
			if (options.filter != undefined && options.filter != null && options.filter != "") {
				url += "&$filter=" + escape(options.filter);
			}
		}

		url += "&$callback=?";

		$.getJSON(url, function (data) {
			if (_processOdataError(data, callback)) return;

			if (data != undefined && data != null) {
				if (data.d != undefined && data.d != null && data.d.results != undefined && data.d.results != null) {
					callback({
						ResponseType: API.ResponseType.OK,
						Message: null,
						ResponseData: data.d.results
					});
				}
			}
		});

	}



	API.GetODataRecord = function (recordTypeName, recordID, callback) {
		var url = _getOdataUrl();
		url += "/" + recordTypeName + "(" + recordID + ")/";
		url += "?$format=json&$callback=?";


		$.getJSON(url, function (data) {

			if (_processOdataError(data, callback)) return;

			if (data != undefined && data != null) {
				if (data.d != undefined && data.d != null) {
					callback({
						ResponseType: API.ResponseType.OK,
						Message: null,
						ResponseData: data.d
					});
				}
			}

		});

	}


	API.ExecCustomProcedure = function (proc, paramArgs, callback) {
		var url = Agility.UGC.API.APIUrl;
		url = url.toLowerCase().replace("agility-ugc-api-jsonp.svc", "ExecCustomProcedure.ashx");
		url += "?proc=" + escape(proc);

		if (paramArgs != undefined && paramArgs != null) {
			for (var propertyName in paramArgs) {
				var val = paramArgs[propertyName];
				if (val == undefined || val == null) continue;
				url += "&@" + escape(propertyName) + "=" + escape(paramArgs[propertyName]);
			}
		}
		url += "&method=?";
		url += "&format=json";
		url += "&accessKey=" + escape(API.APIAccessKey);
		url += "&seconds=" + escape(API.APISeconds);
		url += "&randomNumber=" + escape(API.APIRandomNumber);
		url += "&hash=" + escape(API.APIHash);
		url += "&profileRecordID=" + escape(API.APIProfileRecordID);

		$.getJSON(url, function (data) {

			if (data != undefined) {

				var obj = {
					ResponseType: API.ResponseType.OK,
					Message: null
				};

				if (data.ResonseData != undefined) {
					obj.ResponseData = data.ResonseData;
				} else {
					obj.ResponseData = data.ResponseData;
				}


				//send results to the user...
				callback(obj);
			}
		});
	}

	function _getOdataUrl() {
		//url will look like this http://ugc.agilitycms.com/Agility-UGC-API-JSONP.svc
		//needs to be transformed to this http://ugc.agilitycms.com/Agility-UGC-API-ODATA.svc
		var url = API.APIUrl;
		url = url.substring(0, url.toLowerCase().lastIndexOf("/")) + "/Agility-UGC-API-ODATA.svc";
		return url;
	}

	//does the error callback from OData and returns true if there was an error.
	function _processOdataError(data, callback) {

		if (data != undefined && data != null) {
			if (data.error != undefined && data.error != null) {
				var message = "An error occurred.";

				if (data.error.message != undefined && data.error.message.value != undefined) {
					message = data.error.message.value;
				}
				callback({
					ResponseType: API.ResponseType.Error,
					Message: message,
					ResponseData: null
				});
				return true;
			}
		}
	}


	API.GetMIMEtype = function (filename) {
		/// <summary>
		/// Gets the mime-type of a file based on a filename.
		/// </summary>
		/// <param name="filename" type="String">The file name (from a file upload...)</param>


		var fn = new String(filename);
		var fn1 = new String();
		fn1 = fn.match(/[\/\\][^\/\\]*$/);
		if (fn1 != null && fn1.length > 0) fn = fn1 + "";

		fn = fn.toLowerCase();

		if (fn.search(/^.*\.ai$/) >= 0) {
			return ("application/illustrator");
		}
		if (fn.search(/^.*\.bin$/) >= 0) {
			return ("application/octet-stream");
		}
		if (fn.search(/^.*\.pdf$/) >= 0) {
			return ("application/pdf");
		}
		if (fn.search(/^.*\.ps$/) >= 0) {
			return ("application/postscript");
		}
		if (fn.search(/^.*\.rtf$/) >= 0) {
			return ("application/rtf");
		}
		if (fn.search(/^.*\.sit$/) >= 0) {
			return ("application/stuffit");
		}
		if (fn.search(/^.*\.flv$/) >= 0) {
			return ("video/x-flv");
		}
		if (fn.search(/^.*\.mdb$/) >= 0) {
			return ("application/vnd.ms-access");
		}
		if (fn.search(/^.*\.xls$/) >= 0) {
			return ("application/vnd.ms-excel");
		}
		if (fn.search(/^.*\.ppt$/) >= 0) {
			return ("application/vnd.ms-powerpoint");
		}
		if (fn.search(/^.*\.pps$/) >= 0) {
			return ("application/vnd.ms-powerpoint");
		}
		if (fn.search(/^.*\.pot$/) >= 0) {
			return ("application/vnd.ms-powerpoint");
		}
		if (fn.search(/^.*\.xps$/) >= 0) {
			return ("application/vnd.ms-xpsdocument");
		}
		if (fn.search(/^.*\.doc$/) >= 0) {
			return ("application/msword");
		}
		if (fn.search(/^.*\.7z$/) >= 0) {
			return ("application/x-7z-compressed");
		}
		if (fn.search(/^.*\.torrent$/) >= 0) {
			return ("application/x-bittorrent");
		}
		if (fn.search(/^.*\.tar\.gz$/) >= 0) {
			return ("application/x-compressed-tar");
		}
		if (fn.search(/^.*\.tgz$/) >= 0) {
			return ("application/x-compressed-tar");
		}
		if (fn.search(/^.*\.ttf$/) >= 0) {
			return ("application/x-font-ttf");
		}
		if (fn.search(/^.*\.gz$/) >= 0) {
			return ("application/x-gzip");
		}
		if (fn.search(/^.*\.pdf\.gz$/) >= 0) {
			return ("application/x-gzpdf");
		}
		if (fn.search(/^.*\.ps\.gz$/) >= 0) {
			return ("application/x-gzpostscript");
		}
		if (fn.search(/^.*\.jar$/) >= 0) {
			return ("application/x-java-archive");
		}
		if (fn.search(/^.*\.js$/) >= 0) {
			return ("application/javascript");
		}
		if (fn.search(/^.*\.lzh$/) >= 0) {
			return ("application/x-lha");
		}
		if (fn.search(/^.*\.mkv$/) >= 0) {
			return ("video/x-matroska");
		}
		if (fn.search(/^.*\.exe$/) >= 0) {
			return ("application/x-ms-dos-executable");
		}
		if (fn.search(/^.*\.ogg$/) >= 0) {
			return ("application/ogg");
		}
		if (fn.search(/^.*\.ogx$/) >= 0) {
			return ("application/ogg");
		}
		if (fn.search(/^.*\.oga$/) >= 0) {
			return ("audio/ogg");
		}
		if (fn.search(/^.*\.ogv$/) >= 0) {
			return ("video/ogg");
		}
		if (fn.search(/^.*\.ogg$/) >= 0) {
			return ("audio/x-vorbis+ogg");
		}
		if (fn.search(/^.*\.ogg$/) >= 0) {
			return ("audio/x-flac+ogg");
		}
		if (fn.search(/^.*\.ogg$/) >= 0) {
			return ("audio/x-speex+ogg");
		}
		if (fn.search(/^.*\.spx$/) >= 0) {
			return ("audio/x-speex");
		}
		if (fn.search(/^.*\.ogg$/) >= 0) {
			return ("video/x-theora+ogg");
		}
		if (fn.search(/^.*\.ogm$/) >= 0) {
			return ("video/x-ogm+ogg");
		}
		if (fn.search(/^.*\.qtl$/) >= 0) {
			return ("application/x-quicktime-media-link");
		}
		if (fn.search(/^.*\.tar$/) >= 0) {
			return ("application/x-tar");
		}
		if (fn.search(/^.*\.theme$/) >= 0) {
			return ("application/x-theme");
		}
		if (fn.search(/^.*\.der$/) >= 0) {
			return ("application/x-x509-ca-cert");
		}
		if (fn.search(/^.*\.cer$/) >= 0) {
			return ("application/x-x509-ca-cert");
		}
		if (fn.search(/^.*\.crt$/) >= 0) {
			return ("application/x-x509-ca-cert");
		}
		if (fn.search(/^.*\.cert$/) >= 0) {
			return ("application/x-x509-ca-cert");
		}
		if (fn.search(/^.*\.pem$/) >= 0) {
			return ("application/x-x509-ca-cert");
		}
		if (fn.search(/^.*\.xhtml$/) >= 0) {
			return ("application/xhtml+xml");
		}
		if (fn.search(/^.*\.zip$/) >= 0) {
			return ("application/zip");
		}
		if (fn.search(/^.*\.ac3$/) >= 0) {
			return ("audio/ac3");
		}
		if (fn.search(/^.*\.aiff$/) >= 0) {
			return ("audio/x-aiff");
		}
		if (fn.search(/^.*\.aif$/) >= 0) {
			return ("audio/x-aiff");
		}
		if (fn.search(/^.*\.aifc$/) >= 0) {
			return ("audio/x-aiff");
		}
		if (fn.search(/^.*\.flac$/) >= 0) {
			return ("audio/x-flac");
		}
		if (fn.search(/^.*\.mid$/) >= 0) {
			return ("audio/midi");
		}
		if (fn.search(/^.*\.midi$/) >= 0) {
			return ("audio/midi");
		}
		if (fn.search(/^.*\.kar$/) >= 0) {
			return ("audio/midi");
		}
		if (fn.search(/^.*\.m4a$/) >= 0) {
			return ("audio/mp4");
		}
		if (fn.search(/^.*\.aac$/) >= 0) {
			return ("audio/mp4");
		}
		if (fn.search(/^.*\.mp4$/) >= 0) {
			return ("video/mp4");
		}
		if (fn.search(/^.*\.m4v$/) >= 0) {
			return ("video/mp4");
		}
		if (fn.search(/^.*\.m4b$/) >= 0) {
			return ("audio/x-m4b");
		}
		if (fn.search(/^.*\.3gp$/) >= 0) {
			return ("video/3gpp");
		}
		if (fn.search(/^.*\.3gpp$/) >= 0) {
			return ("video/3gpp");
		}
		if (fn.search(/^.*\.amr$/) >= 0) {
			return ("video/3gpp");
		}
		if (fn.search(/^.*\.mp2$/) >= 0) {
			return ("audio/mp2");
		}
		if (fn.search(/^.*\.mp3$/) >= 0) {
			return ("audio/mpeg");
		}
		if (fn.search(/^.*\.mpga$/) >= 0) {
			return ("audio/mpeg");
		}
		if (fn.search(/^.*\.m3u$/) >= 0) {
			return ("audio/x-mpegurl");
		}
		if (fn.search(/^.*\.vlc$/) >= 0) {
			return ("audio/x-mpegurl");
		}
		if (fn.search(/^.*\.asx$/) >= 0) {
			return ("audio/x-ms-asx");
		}
		if (fn.search(/^.*\.wax$/) >= 0) {
			return ("audio/x-ms-asx");
		}
		if (fn.search(/^.*\.wvx$/) >= 0) {
			return ("audio/x-ms-asx");
		}
		if (fn.search(/^.*\.wmx$/) >= 0) {
			return ("audio/x-ms-asx");
		}
		if (fn.search(/^.*\.psf$/) >= 0) {
			return ("audio/x-psf");
		}
		if (fn.search(/^.*\.wma$/) >= 0) {
			return ("audio/x-ms-wma");
		}
		if (fn.search(/^.*\.ra$/) >= 0) {
			return ("audio/vnd.rn-realaudio");
		}
		if (fn.search(/^.*\.rax$/) >= 0) {
			return ("audio/vnd.rn-realaudio");
		}
		if (fn.search(/^.*\.ram$/) >= 0) {
			return ("application/ram");
		}
		if (fn.search(/^.*\.rv$/) >= 0) {
			return ("video/vnd.rn-realvideo");
		}
		if (fn.search(/^.*\.rvx$/) >= 0) {
			return ("video/vnd.rn-realvideo");
		}
		if (fn.search(/^.*\.wav$/) >= 0) {
			return ("audio/x-wav");
		}
		if (fn.search(/^.*\.bmp$/) >= 0) {
			return ("image/bmp");
		}
		if (fn.search(/^.*\.wbmp$/) >= 0) {
			return ("image/vnd.wap.wbmp");
		}
		if (fn.search(/^.*\.gif$/) >= 0) {
			return ("image/gif");
		}
		if (fn.search(/^.*\.jpeg$/) >= 0) {
			return ("image/jpeg");
		}
		if (fn.search(/^.*\.jpg$/) >= 0) {
			return ("image/jpeg");
		}
		if (fn.search(/^.*\.jpe$/) >= 0) {
			return ("image/jpeg");
		}
		if (fn.search(/^.*\.png$/) >= 0) {
			return ("image/png");
		}
		if (fn.search(/^.*\.rle$/) >= 0) {
			return ("image/rle");
		}
		if (fn.search(/^.*\.svg$/) >= 0) {
			return ("image/svg+xml");
		}
		if (fn.search(/^.*\.svgz$/) >= 0) {
			return ("image/svg+xml-compressed");
		}
		if (fn.search(/^.*\.tif$/) >= 0) {
			return ("image/tiff");
		}
		if (fn.search(/^.*\.tiff$/) >= 0) {
			return ("image/tiff");
		}
		if (fn.search(/^.*\.eps$/) >= 0) {
			return ("image/x-eps");
		}
		if (fn.search(/^.*\.ico$/) >= 0) {
			return ("image/x-ico");
		}
		if (fn.search(/^.*\.psd$/) >= 0) {
			return ("image/x-psd");
		}
		if (fn.search(/^.*\.vcs$/) >= 0) {
			return ("text/calendar");
		}
		if (fn.search(/^.*\.ics$/) >= 0) {
			return ("text/calendar");
		}
		if (fn.search(/^.*\.css$/) >= 0) {
			return ("text/css");
		}
		if (fn.search(/^.*\.CSSL$/) >= 0) {
			return ("text/css");
		}
		if (fn.search(/^.*\.rtx$/) >= 0) {
			return ("text/richtext");
		}
		if (fn.search(/^.*\.rss$/) >= 0) {
			return ("application/rss+xml");
		}
		if (fn.search(/^.*\.atom$/) >= 0) {
			return ("application/atom+xml");
		}
		if (fn.search(/^.*\.opml$/) >= 0) {
			return ("text/x-opml+xml");
		}
		if (fn.search(/^.*\.sgml$/) >= 0) {
			return ("text/sgml");
		}
		if (fn.search(/^.*\.sgm$/) >= 0) {
			return ("text/sgml");
		}
		if (fn.search(/^.*\.dtd$/) >= 0) {
			return ("text/x-dtd");
		}
		if (fn.search(/^.*\.html$/) >= 0) {
			return ("text/html");
		}
		if (fn.search(/^.*\.htm$/) >= 0) {
			return ("text/html");
		}
		if (fn.search(/^.*\.log$/) >= 0) {
			return ("text/x-log");
		}
		if (fn.search(/^README*$/) >= 0) {
			return ("text/x-readme");
		}
		if (fn.search(/^.*\.uri$/) >= 0) {
			return ("text/x-uri");
		}
		if (fn.search(/^.*\.url$/) >= 0) {
			return ("text/x-uri");
		}
		if (fn.search(/^.*\.fo$/) >= 0) {
			return ("text/x-xslfo");
		}
		if (fn.search(/^.*\.xslfo$/) >= 0) {
			return ("text/x-xslfo");
		}
		if (fn.search(/^.*\.xml$/) >= 0) {
			return ("application/xml");
		}
		if (fn.search(/^.*\.xsl$/) >= 0) {
			return ("application/xml");
		}
		if (fn.search(/^.*\.xslt$/) >= 0) {
			return ("application/xml");
		}
		if (fn.search(/^.*\.xbl$/) >= 0) {
			return ("application/xml");
		}
		if (fn.search(/^.*\.mpeg$/) >= 0) {
			return ("video/mpeg");
		}
		if (fn.search(/^.*\.mpg$/) >= 0) {
			return ("video/mpeg");
		}
		if (fn.search(/^.*\.mp2$/) >= 0) {
			return ("video/mpeg");
		}
		if (fn.search(/^.*\.mpe$/) >= 0) {
			return ("video/mpeg");
		}
		if (fn.search(/^.*\.vob$/) >= 0) {
			return ("video/mpeg");
		}
		if (fn.search(/^.*\.m2t$/) >= 0) {
			return ("video/mpeg");
		}
		if (fn.search(/^.*\.qt$/) >= 0) {
			return ("video/quicktime");
		}
		if (fn.search(/^.*\.mov$/) >= 0) {
			return ("video/quicktime");
		}
		if (fn.search(/^.*\.moov$/) >= 0) {
			return ("video/quicktime");
		}
		if (fn.search(/^.*\.qtvr$/) >= 0) {
			return ("video/quicktime");
		}
		if (fn.search(/^.*\.qtif$/) >= 0) {
			return ("image/x-quicktime");
		}
		if (fn.search(/^.*\.qif$/) >= 0) {
			return ("image/x-quicktime");
		}
		if (fn.search(/^.*\.viv$/) >= 0) {
			return ("video/vivo");
		}
		if (fn.search(/^.*\.vivo$/) >= 0) {
			return ("video/vivo");
		}
		if (fn.search(/^.*\.anim[1-9j]$/) >= 0) {
			return ("video/x-anim");
		}
		if (fn.search(/^.*\.fli$/) >= 0) {
			return ("video/x-flic");
		}
		if (fn.search(/^.*\.flc$/) >= 0) {
			return ("video/x-flic");
		}
		if (fn.search(/^.*\.hwp$/) >= 0) {
			return ("application/x-hwp");
		}
		if (fn.search(/^.*\.hwt$/) >= 0) {
			return ("application/x-hwt");
		}
		if (fn.search(/^.*\.mng$/) >= 0) {
			return ("video/x-mng");
		}
		if (fn.search(/^.*\.asf$/) >= 0) {
			return ("video/x-ms-asf");
		}
		if (fn.search(/^.*\.nsc$/) >= 0) {
			return ("application/x-netshow-channel");
		}
		if (fn.search(/^.*\.wmv$/) >= 0) {
			return ("video/x-ms-wmv");
		}
		if (fn.search(/^.*\.avi$/) >= 0) {
			return ("video/x-msvideo");
		}
		if (fn.search(/^.*\.divx$/) >= 0) {
			return ("video/x-msvideo");
		}
		return ("binary/octet-stream");
	}



})(Agility.UGC.API);