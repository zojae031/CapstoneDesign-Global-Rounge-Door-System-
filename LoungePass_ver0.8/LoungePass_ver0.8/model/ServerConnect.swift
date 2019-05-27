//
//  ServerCommunication.swift
//  LoungePass_ver0.8
//
//  Created by MacBook on 25/05/2019.
//  Copyright © 2019 LimSoYul. All rights reserved.
//

import Foundation
import SwiftSocket

class ServerConnect{
    
    private var host = "192.168.123.8"
    private var port = 5050
    private var client: TCPClient
    
    static let sharedInstance = ServerConnect()
    
    init() {
        self.client = TCPClient(address: self.host, port: Int32(self.port))
    }
    
    func connecting() ->Bool {
        
        switch self.client.connect(timeout: 2) {
        case .success:
            return true
        case .failure:
            return false
        }
    }
    
    
    func sendData(string:String) -> Bool{
        
        switch self.client.send(string: string) {
        case .success:
            print("success")
            return true
        case .failure:
            return false
        }
    }
    
    func readResponse() -> String? {
        guard let response = self.client.read(1024*10, timeout: 1) else {return nil}
        return String(bytes: response, encoding: .utf8)
    }
    
    func closing() {
        let converter = ConvertData()
        let _ = sendData(string: converter.getSeqData(seq: "500"))
        self.client.close()
    }
    
    func setIP(newIP :String) {
        self.host = newIP
        self.client = TCPClient(address: self.host, port: Int32(self.port))
        
    }
}
