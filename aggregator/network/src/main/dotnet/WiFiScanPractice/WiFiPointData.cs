using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WiFiScanPractice
{
    /// <summary>
    /// Class to contain data about a WiFi collection point
    /// </summary>
    public class WiFiPointData
    {
        public List<AccessPoint> AccessPoints { get; set; }

        public WiFiPointData()
        {
            this.AccessPoints = new List<AccessPoint>();
        }
    }
}
