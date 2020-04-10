package com.elcom.business.aggregate;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import javax.ws.rs.core.StreamingOutput;

import org.apache.http.HttpStatus;

import com.elcom.data.interview.UnitOfWorkInterview;
import com.elcom.sharedbiz.validation.ValidationException;
import com.elcom.util.StringUtils;

public class MediaAggregate {
	
	private UnitOfWorkInterview _uokInterview = null;
	
	public MediaAggregate(UnitOfWorkInterview _uokEp) {
		this._uokInterview = _uokEp;
	}

	public StreamingOutput mediaProgress(Long mediaId) throws ValidationException, IOException {
		
		String filePath = this._uokInterview.commonRepository().getMediaFilePath(mediaId);
		if( StringUtils.isNullOrEmpty(filePath) )
			throw new ValidationException("mediaId is not valid");
		
		File file = null;
		
		try {
			file = new File(filePath);
		} catch (Exception ex) {
			System.out.println("MediaAggregate.mediaProgress().ex: " + ex.toString());
			throw new ValidationException(HttpStatus.SC_INTERNAL_SERVER_ERROR, "MediaAggregate.mediaProgress(): Error when generate media file");
		}
		
		byte[] bytesArr = Files.readAllBytes(file.toPath());
		
		if ( bytesArr==null || bytesArr.length == 0 )
			throw new ValidationException(HttpStatus.SC_INTERNAL_SERVER_ERROR, "MediaAggregate.mediaProgress(): Error when generate media file");
	    
	    StreamingOutput stream = new StreamingOutput() {
	    	
	        @Override
	        public void write(OutputStream outputStream) throws IOException {
	        	
	          try {
	            
	        	  outputStream.write(bytesArr, 0, bytesArr.length);
	        	  outputStream.flush();
	              
	          } catch (Exception e) {
	             e.printStackTrace();
	          } finally {
	        	  if( outputStream != null )
	        		  outputStream.close();
	          }
	        }
	    };
	    
	    return stream;
	}
}
