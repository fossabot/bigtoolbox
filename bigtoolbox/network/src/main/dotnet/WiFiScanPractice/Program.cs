using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Globalization;
using System.Net;
using System.Net.NetworkInformation;
using System.Security.AccessControl;
using System.Text;
using System.Threading.Tasks;
using Windows.Devices.Enumeration;
using Windows.Devices.Geolocation;
using Windows.Devices.WiFi;
using Windows.Networking.Connectivity;
using Windows.UI.Popups;
using Newtonsoft.Json;

// This example code shows how you could implement the required main function for a 
// Console UWP Application. You can replace all the code inside Main with your own custom code.

// You should also change the Alias value in the AppExecutionAlias Extension in the 
// Package.appxmanifest to a value that you define. To edit this file manually, right-click
// it in Solution Explorer and select View Code, or open it with the XML Editor.

namespace WiFiScanPractice
{
    class Program
    {
        private static WiFiScanner _wifiScanner;

        static void Main(string[] args)
        {
            _wifiScanner = new WiFiScanner();

            for (int i = 0; i < _wifiScanner.Adapters.Count; i++)
            {
                if (_wifiScanner.Adapters[i] == null)
                {
                    Console.WriteLine("Adapter number " + i + " is null!!!!!!!");
                }
                else
                {
                    {
                        Console.WriteLine(_wifiScanner.Adapters[i].NetworkAdapter.NetworkAdapterId);
                        Console.WriteLine();
                    }
                }
            }

//            foreach (NetworkInterface networkInterface in NetworkInterface.GetAllNetworkInterfaces())
//            {
//                Console.WriteLine(networkInterface.ToString());
//                Console.WriteLine(networkInterface.Id);
//                Console.WriteLine(networkInterface.Name);
//                Console.WriteLine(networkInterface.Description);
//            }

            try
            {
                Task<InfoAggregator> info = RunAllWifiScans();
                var result = info.Result;
                Console.WriteLine(JsonConvert.SerializeObject(result));
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }

            ShowNetworkInterfaces();

            Console.WriteLine("Press a key to continue: ");
            Console.ReadLine();
        }

        private static async Task<InfoAggregator> RunAllWifiScans()
        {
            await Program._wifiScanner.ScanForNetworksAll();
            return new InfoAggregator(_wifiScanner);
        }

        private static StringBuilder CreateCsvReport(List<AccessPoint> wifiPoint)
        {
            StringBuilder networkInfo = new StringBuilder();
            networkInfo.AppendLine("MAC,SSID,rssi,kind,Encryption");

            foreach (var wifiSignal in wifiPoint)
            {
                networkInfo.Append($"{wifiSignal.MacAddress},");
                networkInfo.Append($"{wifiSignal.Ssid},");
                networkInfo.Append($"{wifiSignal.NetworkRssiInDecibelMilliwatts},");
                networkInfo.Append($"{wifiSignal.NetworkKind},");
                networkInfo.Append($"{wifiSignal.Encryption}");
                networkInfo.AppendLine();
            }

            return networkInfo;
        }

        private static InfoAggregator CreateInfoAggregator(WiFiScanner scanner)
        {
            return new InfoAggregator(scanner);
        }

        public static void ShowNetworkInterfaces()
        {
            IPGlobalProperties computerProperties = IPGlobalProperties.GetIPGlobalProperties();
            NetworkInterface[] nics = NetworkInterface.GetAllNetworkInterfaces();
            Console.WriteLine("Interface information for {0}.{1}     ",
                computerProperties.HostName, computerProperties.DomainName);
            if (nics == null || nics.Length < 1)
            {
                Console.WriteLine("  No network interfaces found.");
                return;
            }

            Console.WriteLine("  Number of interfaces .................... : {0}", nics.Length);
            foreach (NetworkInterface adapter in nics)
            {
                IPInterfaceProperties properties = adapter.GetIPProperties();
                Console.WriteLine();
                Console.WriteLine(adapter.Description);
                Console.WriteLine(String.Empty.PadLeft(adapter.Description.Length, '='));
                Console.WriteLine("  Interface type .......................... : {0}", adapter.NetworkInterfaceType);
                Console.WriteLine("  Physical Address ........................ : {0}",
                    adapter.GetPhysicalAddress().ToString());
                Console.WriteLine("  Operational status ...................... : {0}",
                    adapter.OperationalStatus);
                string versions = "";

                // Create a display string for the supported IP versions.
                if (adapter.Supports(NetworkInterfaceComponent.IPv4))
                {
                    versions = "IPv4";
                }

                if (adapter.Supports(NetworkInterfaceComponent.IPv6))
                {
                    if (versions.Length > 0)
                    {
                        versions += " ";
                    }

                    versions += "IPv6";
                }

                Console.WriteLine("  IP version .............................. : {0}", versions);
                ShowIPAddresses(properties);

                // The following information is not useful for loopback adapters.
                if (adapter.NetworkInterfaceType == NetworkInterfaceType.Loopback)
                {
                    continue;
                }

                Console.WriteLine("  DNS suffix .............................. : {0}",
                    properties.DnsSuffix);

                string label;
                if (adapter.Supports(NetworkInterfaceComponent.IPv4))
                {
                    IPv4InterfaceProperties ipv4 = properties.GetIPv4Properties();
                    Console.WriteLine("  MTU...................................... : {0}", ipv4.Mtu);
                    if (ipv4.UsesWins)
                    {

                        IPAddressCollection winsServers = properties.WinsServersAddresses;
                        if (winsServers.Count > 0)
                        {
                            label = "  WINS Servers ............................ :";
                            ShowIPAddresses(label, winsServers);
                        }
                    }
                }

                Console.WriteLine("  DNS enabled ............................. : {0}",
                    properties.IsDnsEnabled);
                Console.WriteLine("  Dynamically configured DNS .............. : {0}",
                    properties.IsDynamicDnsEnabled);
                Console.WriteLine("  Receive Only ............................ : {0}",
                    adapter.IsReceiveOnly);
                Console.WriteLine("  Multicast ............................... : {0}",
                    adapter.SupportsMulticast);
                ShowInterfaceStatistics(adapter);

                Console.WriteLine();
            }
        }

        private static void ShowIPAddresses(string label, IPAddressCollection winsServers)
        {
            throw new NotImplementedException();
        }

        private static void ShowInterfaceStatistics(NetworkInterface adapter)
        {
            IPv4InterfaceStatistics stats = adapter.GetIPv4Statistics();
            Console.WriteLine(" Stats:");
            Console.WriteLine(" Packets Received ....... : {0}",
                stats.UnicastPacketsReceived);
            Console.WriteLine(" Bytes Sent ............. : {0}",
                stats.BytesSent);
        }

        public static void ShowIPAddresses(IPInterfaceProperties adapterProperties)
        {
            IPAddressCollection dnsServers = adapterProperties.DnsAddresses;
            if (dnsServers != null)
            {
                foreach (IPAddress dns in dnsServers)
                {
                    Console.WriteLine("  DNS Servers ............................. : {0}",
                        dns.ToString()
                    );
                }
            }

            IPAddressInformationCollection anyCast = adapterProperties.AnycastAddresses;
            if (anyCast != null)
            {
                foreach (IPAddressInformation any in anyCast)
                {
                    Console.WriteLine("  Anycast Address .......................... : {0} {1} {2}",
                        any.Address,
                        any.IsTransient ? "Transient" : "",
                        any.IsDnsEligible ? "DNS Eligible" : ""
                    );
                }

                Console.WriteLine();
            }

            MulticastIPAddressInformationCollection multiCast = adapterProperties.MulticastAddresses;
            if (multiCast != null)
            {
                foreach (IPAddressInformation multi in multiCast)
                {
                    Console.WriteLine("  Multicast Address ....................... : {0} {1} {2}",
                        multi.Address,
                        multi.IsTransient ? "Transient" : "",
                        multi.IsDnsEligible ? "DNS Eligible" : ""
                    );
                }

                Console.WriteLine();
            }

            UnicastIPAddressInformationCollection uniCast = adapterProperties.UnicastAddresses;
            if (uniCast != null)
            {
                string lifeTimeFormat = "dddd, MMMM dd, yyyy  hh:mm:ss tt";
                foreach (UnicastIPAddressInformation uni in uniCast)
                {
                    DateTime when;

                    Console.WriteLine("  Unicast Address ......................... : {0}", uni.Address);
                    Console.WriteLine("     Prefix Origin ........................ : {0}", uni.PrefixOrigin);
                    Console.WriteLine("     Suffix Origin ........................ : {0}", uni.SuffixOrigin);
                    Console.WriteLine("     Duplicate Address Detection .......... : {0}",
                        uni.DuplicateAddressDetectionState);

                    // Format the lifetimes as Sunday, February 16, 2003 11:33:44 PM
                    // if en-us is the current culture.

                    // Calculate the date and time at the end of the lifetimes.    
                    when = DateTime.UtcNow + TimeSpan.FromSeconds(uni.AddressValidLifetime);
                    when = when.ToLocalTime();
                    Console.WriteLine("     Valid Life Time ...................... : {0}",
                        when.ToString(lifeTimeFormat, System.Globalization.CultureInfo.CurrentCulture)
                    );
                    when = DateTime.UtcNow + TimeSpan.FromSeconds(uni.AddressPreferredLifetime);
                    when = when.ToLocalTime();
                    Console.WriteLine("     Preferred life time .................. : {0}",
                        when.ToString(lifeTimeFormat, System.Globalization.CultureInfo.CurrentCulture)
                    );

                    when = DateTime.UtcNow + TimeSpan.FromSeconds(uni.DhcpLeaseLifetime);
                    when = when.ToLocalTime();
                    Console.WriteLine("     DHCP Leased Life Time ................ : {0}",
                        when.ToString(lifeTimeFormat, System.Globalization.CultureInfo.CurrentCulture)
                    );
                }

                Console.WriteLine();
            }
        }
    }

    class InfoAggregator
    {
        public List<NetAdapter> Adapters { get; set; }

        public InfoAggregator(WiFiScanner scanner)
        {
            this.Adapters = new List<NetAdapter>();
            foreach (var adapter in scanner.Adapters)
            {
                if (adapter != null)
                {
                    this.Adapters.Add(new NetAdapter(adapter));
                }
            }
        }
    }

    class NetAdapter
    {
        public String AdapterName;
        public string LocalKnownName;
        public Guid NetworkAdapterId;
        public Enum OperationalStatus;
        public String[] IPVersions;
        public uint IanaInterfaceType;
        public List<AccessPoint> AccessPoints;
        

        public NetAdapter(WiFiAdapter tAdapter)
        {
            NetworkInterface representativeInterface = GetNetworkInterface(tAdapter);
            this.AdapterName = representativeInterface.Description;
            this.LocalKnownName = representativeInterface.Name;
            this.NetworkAdapterId = tAdapter.NetworkAdapter.NetworkAdapterId;
            this.OperationalStatus = representativeInterface.OperationalStatus;
            this.IPVersions = GetSupportedIpVersions(representativeInterface);
            this.IanaInterfaceType = tAdapter.NetworkAdapter.IanaInterfaceType;
            this.AccessPoints = new List<AccessPoint>();
            foreach (var availableNetwork in tAdapter.NetworkReport.AvailableNetworks)
            {
                var wifiSignal = new AccessPoint()
                {
                    MacAddress = availableNetwork.Bssid,
                    Ssid = availableNetwork.Ssid,
                    ChannelCenterFrequencyInKilohertz = availableNetwork.ChannelCenterFrequencyInKilohertz,
                    NetworkKind = availableNetwork.NetworkKind.ToString(),
                    NetworkRssiInDecibelMilliwatts = availableNetwork.NetworkRssiInDecibelMilliwatts,
                    Encryption = availableNetwork.SecuritySettings.NetworkEncryptionType.ToString()
                };
                AccessPoints.Add(wifiSignal);
            }
        }

        private String[] GetSupportedIpVersions(NetworkInterface inInterface)
        {
            String[] output = new String[2];
            if (inInterface.Supports(NetworkInterfaceComponent.IPv4))
            {
                output[0] = "IPv4";
            }
            if (inInterface.Supports(NetworkInterfaceComponent.IPv6))
            {
                output[0] = "IPv6";
            }
            return output;
        }

        private NetworkInterface GetNetworkInterface(WiFiAdapter adapterIn)
        {
            NetworkInterface[] nics = NetworkInterface.GetAllNetworkInterfaces();
            foreach (NetworkInterface nic in nics)
            {
                Guid interfaceGuid = new Guid(nic.Id);
                if (interfaceGuid.Equals(adapterIn.NetworkAdapter.NetworkAdapterId))
                {
                    return nic;
                }
            }
            throw new Exception("No matching NetworkInterface found for this WiFiAdapter within NetworkInterface.GetAllNetworkInterfaces()");
        }
    }
}
