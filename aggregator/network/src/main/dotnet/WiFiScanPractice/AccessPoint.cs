using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WiFiScanPractice
{
    public class AccessPoint
    {
        public string MacAddress { get; set; }

        public string Ssid { get; set; }

        public string NetworkKind { get; set; }

        public double NetworkRssiInDecibelMilliwatts { get; set; }

        public double ChannelCenterFrequencyInKilohertz { get; set; }

        public string Encryption { get; set; }

        //TODO: add security details
    }
}
